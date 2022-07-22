package com.daunjo.cart.common.entity;

import com.daunjo.cart.common.dto.ProductDto;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;

/**
 * 상품 정보 entity
 */
@Entity
@Getter
@NoArgsConstructor
public class Product extends BaseEntity {

    @Id
    @Column(name="proudct_id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long productId;

    @Column(name="group_cd", nullable = false)
    private String groupCd;

    @Column(name="product_nm", nullable = false)
    private String productNm;

    @Column(name="amt", nullable = false)
    private BigDecimal amt;

    @Column(name="stock_cnt", nullable = false)
    private Long stockCnt;

    @Builder
    private Product(Long productId, String groupCd, String productNm, BigDecimal amt, Long stockCnt){
        this.productId = productId;
        this.groupCd = groupCd;
        this.productNm = productNm;
        this.amt = amt;
        this.stockCnt = stockCnt;
    }

    @Builder(builderClassName = "noPkBuilder", builderMethodName = "noPkBuilder")
    private Product(String groupCd, String productNm, BigDecimal amt, Long stockCnt){
        this.groupCd = groupCd;
        this.productNm = productNm;
        this.amt = amt;
        this.stockCnt = stockCnt;
    }

    /**
     * 상품 entity -> dto
     * @param entity
     * @return
     */
    public ProductDto toDto(Product entity){
        ProductDto dto = new ProductDto();
        dto.setProductId(entity.getProductId());
        dto.setProductNm(entity.getProductNm());
        dto.setGroupCd(entity.getGroupCd());
        dto.setAmt(entity.getAmt());
        dto.setStockCnt(entity.getStockCnt());
        return dto;
    }

    /**
     * 상품 dto ->   enttiy
     * @param dto
     * @return
     */
    public Product toEntity(ProductDto dto){
        Product entity = Product.builder()
                .productId(dto.getProductId())
                .productNm(dto.getProductNm())
                .groupCd(dto.getGroupCd())
                .amt(dto.getAmt())
                .stockCnt(dto.getStockCnt())
                .build();
        return entity;
    }

    public void updateCnt(Long cnt){
        this.stockCnt = cnt;
    }

}
