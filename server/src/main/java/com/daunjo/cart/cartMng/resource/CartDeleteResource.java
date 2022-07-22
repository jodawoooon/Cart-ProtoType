package com.daunjo.cart.cartMng.resource;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotEmpty;
import java.util.List;


/**
 * 장바구니 삭제 대상 목록 객체
 */
@Getter
@Setter
@ToString
@Schema(description = "장바구니 삭제 대상 목록 객체 ")
public class CartDeleteResource {

    @Schema(description = "장바구니 삭제 대상 리스트", required = true)
    @NotEmpty
    List<Long> deleteIdList;

}
