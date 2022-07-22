package com.daunjo.cart.common.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import net.bytebuddy.implementation.bind.annotation.Default;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

/**
 * 장바구니 DTO
 */
@Getter
@Setter
@ToString
public class CartDto {

    private Long cartId;        //장바구니 PK

    private String groupCd;     //상품 배송그룹

    private String groupNm;     //상품 배송그룹명

    @NotNull
    private Long productId;     //상품id

    private String productNm;   //상품명

    @NotNull
    private Long cnt;           //장바구니수량

    private BigDecimal amt;     //상품금액

    private boolean checked = true;    //선택여부

}
