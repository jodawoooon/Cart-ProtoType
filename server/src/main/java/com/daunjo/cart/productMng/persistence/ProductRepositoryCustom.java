package com.daunjo.cart.productMng.persistence;

import com.daunjo.cart.common.dto.ProductDto;
import com.daunjo.cart.productMng.dto.ProductSearchDto;

import java.util.List;

/**
 * 상품 정보 데이터베이스 접근을 위한 Repository 인터페이스
 */
public interface ProductRepositoryCustom {

    /**
     * 상품 목록 다건 조회
     * @param searchDto
     * @return
     */
    List<ProductDto> searchList(ProductSearchDto searchDto);
}
