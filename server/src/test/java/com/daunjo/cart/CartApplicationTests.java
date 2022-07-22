package com.daunjo.cart;

import com.daunjo.cart.common.dto.ProductDto;
import com.daunjo.cart.common.entity.Product;
import com.daunjo.cart.common.persistence.ProductRepository;
import com.daunjo.cart.productMng.dto.ProductSearchDto;
import com.daunjo.cart.productMng.service.ProductMngService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.math.BigDecimal;
import java.util.List;

@SpringBootTest
class CartApplicationTests {

	@Autowired
	ProductRepository productRepository;

	@Autowired
	ProductMngService productMngService;


	@Test
	void searchProductPage(){
		ProductSearchDto searchDto = new ProductSearchDto();
		searchDto.setPage(1);
		searchDto.setSizePerPage(9);

		List<ProductDto> dtoList = productMngService.searchList(searchDto);
		System.out.println(">>>>>>>>>>>dtoList");
		for (ProductDto dto : dtoList){
			System.out.println(dto);
		}

		PageRequest pageRequest = PageRequest.of(searchDto.getPage(), searchDto.getSizePerPage());
		Page<ProductDto> dtoPage = productMngService.searchPage(searchDto, pageRequest);
		System.out.println(">>>>>>>>>>>dtoPage");
		for (ProductDto dto : dtoPage.getContent()){
			System.out.println(dto);
		}
	}

	@Test
	void saveProduct(){

		productRepository.save(Product.noPkBuilder()
						.amt(BigDecimal.valueOf(25000L))
						.stockCnt(5L)
						.productNm("나이키 헤리티지 스우시 캡")
						.groupCd("30")
				.build());

		ProductSearchDto searchDto = new ProductSearchDto();
		searchDto.setPage(1);
		searchDto.setSizePerPage(10);

		List<ProductDto> productPage = productMngService.searchList(searchDto);

		ProductDto productDto = productPage.get(0);
		Product product = productMngService.get(productDto.getProductId());
		System.out.println(">>>CreateDate : "+product.getCreatedDate());
		System.out.println(">>>LastModifiedDate : "+product.getLastModifiedDate());

	}
	@Test
	void contextLoads() {
	}

}
