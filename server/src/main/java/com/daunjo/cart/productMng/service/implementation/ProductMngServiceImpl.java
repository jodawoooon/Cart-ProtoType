package com.daunjo.cart.productMng.service.implementation;

import com.daunjo.cart.common.dto.CartDto;
import com.daunjo.cart.common.dto.ProductDto;
import com.daunjo.cart.common.entity.Product;
import com.daunjo.cart.common.persistence.ProductRepository;
import com.daunjo.cart.productMng.dto.ProductSearchDto;
import com.daunjo.cart.productMng.persistence.ProductRepositoryCustom;
import com.daunjo.cart.productMng.service.ProductMngService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.NoSuchElementException;

/**
 * 상품 정보 비즈니스 로직 처리를 위한 서비스 구현 정의
 */
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ProductMngServiceImpl implements ProductMngService {

    private final ProductRepository productRepository;
    private final ProductRepositoryCustom productRepositoryCustom;

    /**
     * 상품 목록 페이지 조회
     * @param searchDto
     * @param pageRequest
     * @return
     */
    @Override
    public Page<ProductDto> searchPage(ProductSearchDto searchDto, PageRequest pageRequest) {
        List<ProductDto> dtoList = searchList(searchDto);

        //페이징 처리
        int start = (int) pageRequest.getOffset();
        int end = Math.min((start + pageRequest.getPageSize()), dtoList.size());
        return new PageImpl<>(dtoList.subList(start, end), pageRequest, dtoList.size());
    }

    /**
     * 상품 목록 조회
     * @param searchDto
     * @return
     */
    @Override
    public List<ProductDto> searchList(ProductSearchDto searchDto) {
        return productRepositoryCustom.searchList(searchDto);
    }

    /**
     * 상품 entity 단건 조회
     * @param productId
     * @return
     */
    @Override
    public Product get(Long productId) {
        if(productId == null)
            throw new NoSuchElementException("조회, 수정, 삭제 시 ID값은 필수입니다.");

        return productRepository.findById(productId)
                .orElseThrow(() -> new NoSuchElementException("해당하는 상품을 찾을 수 없습니다."));
    }

    /**
     * 상품 잔여 재고 수량 확인
     * @param createDto
     */
    @Override
    public void checkStockCnt(CartDto createDto) {
        Product product = get(createDto.getProductId());
        if(product.getStockCnt() < createDto.getCnt())
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "상품 재고 수량이 부족합니다. ( 남은 수량 : "+product.getStockCnt()+"개 )");
    }

    /**
     * 상품 잔여 재고 수량 변경
     * @param item
     */
    @Override
    @Transactional
    public void updateStockCnt(CartDto item) {
        Product product = get(item.getProductId());
        product.updateCnt(product.getStockCnt() - item.getCnt());
    }
}
