package com.daunjo.cart.cartMng.persistence;

import com.daunjo.cart.common.dto.CartDto;
import com.daunjo.cart.common.entity.Cart;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.daunjo.cart.common.entity.QCart.cart;
import static com.daunjo.cart.common.entity.QDeliveryGroup.deliveryGroup;
import static com.daunjo.cart.common.entity.QProduct.product;



/**
 * 장바구니 데이터베이스 접근을 위한 Repository 구현
 */
@Repository
public class CartRepositoryImpl extends QuerydslRepositorySupport implements CartRepositoryCustom {

    private final JPAQueryFactory jpaQueryFactory;

    public CartRepositoryImpl(JPAQueryFactory jpaQueryFactory) {super(Cart.class);
        this.jpaQueryFactory = jpaQueryFactory;
    };

    /**
     * 장바구니 목록 조회
     * @return
     */
    @Override
    public List<CartDto> searchList() {
        return jpaQueryFactory.select(
                Projections.bean(
                        CartDto.class,
                        cart.cartId,
                        cart.productId,
                        cart.cnt,
                        product.productNm,
                        product.amt,
                        product.groupCd,
                        deliveryGroup.groupNm
                )
        )
                .from(cart)
                .innerJoin(product).on(cart.productId.eq(product.productId))
                .innerJoin(deliveryGroup).on(product.groupCd.eq(deliveryGroup.groupCd))
                .fetch();
    }
}
