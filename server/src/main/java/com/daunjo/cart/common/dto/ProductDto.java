package com.daunjo.cart.common.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;

/**
 * 상품 DTO
 */
@Getter
@Setter
@ToString
public class ProductDto {
    private Long productId;     //상품id
    private String groupCd;     //배송그룹코드
    private String groupNm;     //배송그룹명
    private String productNm;   //상품명
    private BigDecimal amt;     //상품금액
    private Long stockCnt;      //재고수량
}
