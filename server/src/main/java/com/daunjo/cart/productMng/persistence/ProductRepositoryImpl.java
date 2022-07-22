package com.daunjo.cart.productMng.persistence;

import com.daunjo.cart.common.dto.ProductDto;
import com.daunjo.cart.common.entity.Product;
import com.daunjo.cart.productMng.dto.ProductSearchDto;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.daunjo.cart.common.entity.QDeliveryGroup.deliveryGroup;
import static com.daunjo.cart.common.entity.QProduct.product;

/**
 * 상품 정보 데이터베이스 접근을 위한 Repository 구현
 */
@Repository
public class ProductRepositoryImpl extends QuerydslRepositorySupport implements ProductRepositoryCustom {

    public ProductRepositoryImpl(JPAQueryFactory jpaQueryFactory) {
        super(Product.class);
        this.jpaQueryFactory = jpaQueryFactory;
    }

    private final JPAQueryFactory jpaQueryFactory;

    /**
     * 상품 목록 다건 조회
     * @param searchDto
     * @return
     */
    @Override
    public List<ProductDto> searchList(ProductSearchDto searchDto) {
        BooleanBuilder builder = filtering(searchDto);
        JPAQuery<ProductDto> query = jpaQueryFactory.select(
                Projections.bean(
                        ProductDto.class,
                        product.productId,
                        product.groupCd,
                        deliveryGroup.groupNm,
                        product.productNm,
                        product.amt,
                        product.stockCnt
                )
        ).from(product)
                .where(builder)
                .join(deliveryGroup).on(product.groupCd.eq(deliveryGroup.groupCd));

        query = applySortOption(searchDto.getSortOption(), query);
        return query.fetch();
    }

    /**
     * 상품 목록 필터링
     * @param searchDto
     * @return
     */
    private BooleanBuilder filtering(ProductSearchDto searchDto){
        BooleanBuilder builder = new BooleanBuilder();
        if(searchDto.getFilterList() != null && !searchDto.getFilterList().isEmpty()){
            builder.and(product.groupCd.in(searchDto.getFilterList()));
        }

        return builder;
    }

    /**
     * 상품 목록 정렬 옵션 적용
     * @param sortOption
     * @param query
     * @return
     */
    private JPAQuery<ProductDto> applySortOption(String sortOption, JPAQuery<ProductDto> query) {
        if(sortOption != null){
            switch (sortOption){
                case "alphabetical":
                    query.orderBy(product.productNm.asc());
                    break;
                case "new":
                    query.orderBy(product.createdDate.desc());
                    break;
                case "lowest":
                    query.orderBy(product.amt.asc());
                    break;
                case "highest":
                    query.orderBy(product.amt.desc());
                    break;
            }
        }
        return query;
    }
}
