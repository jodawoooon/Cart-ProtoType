package com.daunjo.cart.cartMng.resource;

import com.daunjo.cart.common.dto.CartDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * 장바구니 목록 조회 Resource
 */
@Getter
@Setter
@Schema(description = "장바구니 목록 리스트")
public class CartResource {

    @Schema(description = "쓱배송 그룹 장바구니 목록 리스트")
    List<CartDto> ssgCartList;

    @Schema(description = "새벽배송 그룹 장바구니 목록 리스트")
    List<CartDto> freshCartList;

    @Schema(description = "일반택배배송 그룹 장바구니 목록 리스트")
    List<CartDto> basicCartList;

}
