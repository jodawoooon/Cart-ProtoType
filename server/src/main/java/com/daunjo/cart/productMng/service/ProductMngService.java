package com.daunjo.cart.productMng.service;

import com.daunjo.cart.common.dto.CartDto;
import com.daunjo.cart.common.dto.ProductDto;
import com.daunjo.cart.common.entity.Product;
import com.daunjo.cart.productMng.dto.ProductSearchDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.List;

/**
 * 상품 정보 비즈니스 로직 처리를 위한 Serivce 인터페이스
 */
public interface ProductMngService {

    /**
     * 상품 목록 다건 페이지 조회
     * @param searchDto
     * @param pageRequest
     * @return
     */
    Page<ProductDto> searchPage(ProductSearchDto searchDto, PageRequest pageRequest);

    /**
     * 상품 목록 다건 목록 조회
     * @param searchDto
     * @return
     */
    List<ProductDto> searchList(ProductSearchDto searchDto);

    /**
     * 상품 Entity 단건 조회
     * @param productId
     * @return
     */
    Product get(Long productId);

    /**
     * 상품 잔여 재고 수량 확인
     * @param createDto
     */
    void checkStockCnt(CartDto createDto);

    /**
     * 상품 잔여 재고 수량 변경
     * @param item
     */
    void updateStockCnt(CartDto item);
}
