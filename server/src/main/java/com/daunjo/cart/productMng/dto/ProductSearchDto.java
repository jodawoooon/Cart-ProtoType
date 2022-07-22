package com.daunjo.cart.productMng.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;


/**
 * 상품 목록 조회 조건 DTO
 */
@Getter
@Setter
@ToString
@Schema(description = "상품 목록 조회 조건 DTO")
public class ProductSearchDto {

    @Schema(description = "page", required = true)
    @NotNull
    Integer page;

    @Schema(description = "sizePerPage", required = true)
    @NotNull
    Integer sizePerPage;

    @Schema(description = "상품목록 정렬 조건", required = true)
    @NotEmpty
    String sortOption;

    @Schema(description = "상품목록 필터링 조건 리스트", required = true)
    @NotEmpty
    List<String> filterList;

}
