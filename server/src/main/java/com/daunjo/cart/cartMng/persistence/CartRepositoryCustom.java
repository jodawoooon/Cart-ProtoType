package com.daunjo.cart.cartMng.persistence;

import com.daunjo.cart.common.dto.CartDto;

import java.util.List;

/**
 * 장바구니 데이터베이스 접근을 위한 Repository 인터페이스
 */
public interface CartRepositoryCustom {

    /**
     * 장바구니 목록 다건 조회
     * @return
     */
    List<CartDto> searchList();
}
