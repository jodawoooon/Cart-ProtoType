package com.daunjo.cart.productMng.controller;

import com.daunjo.cart.common.dto.ProductDto;
import com.daunjo.cart.productMng.dto.ProductSearchDto;
import com.daunjo.cart.productMng.service.ProductMngService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * 상품 정보 관련 API 요청 처리를 위한 컨트롤러 정의
 */
@RestController
@RequestMapping("/api/product")
@Tag(name = "Product", description = "상품 정보 관련 API 요청 처리를 위한 컨트롤러")
@CrossOrigin
@RequiredArgsConstructor
public class ProductMngController {

    private final ProductMngService productMngService;

    @Operation(summary = "상품 목록 다건(페이지) 조회", description= "상품 목록을 다건 조회한다.")
    @GetMapping("/page")
    public Page<ProductDto> searchPage(@Valid ProductSearchDto searchDto){
        PageRequest pageRequest = PageRequest.of(searchDto.getPage(), searchDto.getSizePerPage());
        return productMngService.searchPage(searchDto, pageRequest);
    }

}
