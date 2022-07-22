package com.daunjo.cart.cartMng.service.implementation;

import com.daunjo.cart.cartMng.persistence.CartRepositoryCustom;
import com.daunjo.cart.cartMng.resource.CartResource;
import com.daunjo.cart.cartMng.service.CartMngService;
import com.daunjo.cart.common.dto.CartDto;
import com.daunjo.cart.common.entity.Cart;
import com.daunjo.cart.common.persistence.CartRepository;
import com.daunjo.cart.productMng.service.ProductMngService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * 장바구니 정보 비즈니스 로직 처리를 위한 서비스 구현 정의
 */
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class CartMngServiceImpl implements CartMngService {

    private final CartRepository cartRepository;
    private final CartRepositoryCustom cartRepositoryCustom;

    private final ProductMngService productMngService;

    /**
     * 장바구니 목록 조회
     * @return
     */
    @Override
    public CartResource searchList() {
        CartResource result = new CartResource();

        List<CartDto> dtoList = cartRepositoryCustom.searchList();

        //장바구니 리스트를 배송 그룹별로 분류한다.
        Map<String, List<CartDto>> groupingByGroupCd = dtoList
                .stream()
                .collect(Collectors.groupingBy(CartDto::getGroupCd));
        result.setSsgCartList(groupingByGroupCd.get("10"));
        result.setFreshCartList(groupingByGroupCd.get("20"));
        result.setBasicCartList(groupingByGroupCd.get("30"));

        return result;
    }

    /**
     * 장바구니 물건 구매하기
     * @param dtoList
     */
    @Override
    @Transactional
    public void buy(List<CartDto> dtoList) {
        System.out.println(dtoList);

        //1. 상품 재고 수정 -> "구매하기"가 정상완료된 상품(들)은 장바구니 목록에서 제거된다.
        dtoList.forEach(
                item -> {
                    productMngService.updateStockCnt(item);
                    delete(item.getCartId());
                }
        );
    }

    /**
     * 장바구니 단건 등록
     * @param createDto
     * @return
     */
    @Override
    @Transactional
    public CartDto save(CartDto createDto) {
        //1. 남은 재고 수량 가능한지 체크
        productMngService.checkStockCnt(createDto);

        //2. 장바구니에 이미 있는 상품인지 체크
        Optional<Cart> updateEntity = cartRepository.findByProductId(createDto.getProductId());
        if(updateEntity.isEmpty()) {
            //3. 장바구니에 없다면 새로 등록
            cartRepository.save(Cart.toEntity(createDto));

            return createDto;
        }
        //3. 장바구니에 이미 담겨있다면 구매 수량 수정
        Long updateCnt = updateEntity.get().getCnt() + 1;
        CartDto updateDto = Cart.toDto(updateEntity.get());
        updateDto.setCnt(updateCnt);

        return update(updateDto);
    }

    /**
     * 장바구니 구매 수량 단건 수정
     * @param updateDto
     * @return
     */
    @Override
    @Transactional
    public CartDto update(CartDto updateDto) {
        //1. 남은 재고 수량 가능한지 체크
        productMngService.checkStockCnt(updateDto);

        Cart updateEntity = get(updateDto.getCartId());
        updateEntity.updateCnt(updateDto.getCnt());

        return Cart.toDto(updateEntity);
    }

    /**
     * 장바구니 정보 다건 삭제
     * @param deleteDtoList
     */
    @Override
    @Transactional
    public void deleteList(List<Long>deleteDtoList) {
        deleteDtoList.forEach(this::delete);
    }

    /**
     * 장바구니 정보 단건 삭제
     * @param cartId
     */
    @Override
    @Transactional
    public void delete(Long cartId) {
        Cart deleteEntity = get(cartId);
        cartRepository.delete(deleteEntity);
    }

    /**
     * 장바구니 entity 단건 조회
     * @param cartId
     * @return
     */
    @Override
    public Cart get(Long cartId) {
        if(cartId == null)
            throw new NoSuchElementException("조회, 수정, 삭제 시 ID값은 필수입니다.");

        return cartRepository.findById(cartId)
                .orElseThrow(() -> new NoSuchElementException("해당하는 상품을 찾을 수 없습니다."));
    }
}
