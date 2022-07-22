package com.daunjo.cart.common.entity;

import com.daunjo.cart.common.dto.CartDto;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * 장바구니 Entity
 */
@Entity
@Getter
@NoArgsConstructor
public class Cart extends BaseEntity{

    @Id
    @Column(name="cart_id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long cartId;

    @Column(name="product_id", nullable = false)
    private Long productId;

    @Column(name="cnt", nullable = false)
    private Long cnt;

    @Builder()
    private Cart(Long cartId, Long productId, Long cnt){
        this.cartId = cartId;
        this.productId = productId;
        this.cnt = cnt;
    }

    /**
     * 장바구니 entity -> dto
     * @param entity
     * @return
     */
    public static CartDto toDto(Cart entity){
        CartDto dto = new CartDto();
        dto.setCartId(entity.getCartId());
        dto.setProductId(entity.getProductId());
        dto.setCnt(entity.getCnt());
        return dto;
    }

    /**
     * 장바구니 dto -> entity
     * @param dto
     * @return
     */
    public static Cart toEntity(CartDto dto){
        Cart entity = Cart.builder()
                .cartId(dto.getCartId())
                .productId(dto.getProductId())
                .cnt(dto.getCnt())
                .build();
        return entity;
    }

    public void updateCnt(Long cnt){
        this.cnt = cnt;
    }

}
