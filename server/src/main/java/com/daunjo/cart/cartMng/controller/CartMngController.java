package com.daunjo.cart.cartMng.controller;

import com.daunjo.cart.cartMng.resource.CartDeleteResource;
import com.daunjo.cart.cartMng.resource.CartResource;
import com.daunjo.cart.cartMng.service.CartMngService;
import com.daunjo.cart.common.dto.CartDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * 장바구니 관련 API 요청 처리를 위한 컨트롤러 정의
 */
@RestController
@RequestMapping("/api/cart")
@Tag(name = "Cart", description = "장바구니 관련 API 요청 처리를 위한 컨트롤러")
@RequiredArgsConstructor
@CrossOrigin
public class CartMngController {

    private final CartMngService cartMngService;

    @Operation(summary = "장바구니 상품 다건 구매", description= "장바구니에 담긴 상품을 다건 구매한다.")
    @PostMapping("/buy")
    public void buy(@RequestBody @Valid List<CartDto> buyDtoList){
        cartMngService.buy(buyDtoList);
    }

    @Operation(summary = "장바구니 목록 다건 조회", description= "장바구니에 담긴 상품을 상품 그룹별로 조회한다.")
    @GetMapping("/page")
    public CartResource searchPage(){
        return cartMngService.searchList();
    }

    @Operation(summary = "장바구니 상품 단건 저장", description= "장바구니에 상품을 추가한다.")
    @PostMapping("/carts")
    public CartDto save(@RequestBody @Valid CartDto createDto){
        return cartMngService.save(createDto);
    }

    @Operation(summary = "장바구니 상품 단건 수정", description= "장바구니에 담긴 상품의 갯수를 변경한다.")
    @PutMapping("/carts")
    public CartDto update(@RequestBody @Valid CartDto updateDto){
        return cartMngService.update(updateDto);
    }

    @Operation(summary = "장바구니 상품 다건 삭제", description= "장바구니에 담긴 상품을 다건 삭제한다.")
    @DeleteMapping("/carts")
    public void deleteList(CartDeleteResource deleteResource){
        cartMngService.deleteList(deleteResource.getDeleteIdList());
    }

    @Operation(summary = "장바구니 상품 단건 삭제", description= "장바구니에 담긴 상품을 단건 삭제한다.")
    @DeleteMapping("/{cartId}")
    public void delete(@PathVariable("cartId") Long cartId){
        cartMngService.delete(cartId);
    }

}
