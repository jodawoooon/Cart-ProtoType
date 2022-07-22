package com.daunjo.cart.cartMng.service;

import com.daunjo.cart.cartMng.resource.CartResource;
import com.daunjo.cart.common.dto.CartDto;
import com.daunjo.cart.common.entity.Cart;

import java.util.List;

/**
 * 장바구니 정보 비즈니스 로직 처리를 위한 Serivce 인터페이스
 */
public interface CartMngService {

    /**
     * 장바구니 목록 다건 목록 조회
     * @param searchDto
     * @return
     */
    CartResource searchList();

    /**
     * 장바구니 구매하기
     * @param dtoList
     */
    void buy(List<CartDto> dtoList);

    /**
     * 장바구니 단건 등록
     * @param createDto
     * @return
     */
    CartDto save(CartDto createDto);

    /**
     * 장바구니 상태 단건 수정
     * @param updateDto
     * @return
     */
    CartDto update(CartDto updateDto);

    /**
     * 장바구니 다건 삭제
     * @param deleteDtoList
     */
    void deleteList(List<Long> deleteDtoList);

    /**
     * 장바구니 단건 삭제
     * @param cartId
     */
    void delete(Long cartId);

    /**
     * 장바구니 Entity 단건 조회
     * @param cartId
     * @return
     */
    Cart get(Long cartId);

}
