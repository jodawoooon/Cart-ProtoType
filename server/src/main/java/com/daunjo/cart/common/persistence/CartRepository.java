package com.daunjo.cart.common.persistence;


import com.daunjo.cart.common.entity.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CartRepository extends JpaRepository<Cart, Long> {

    Optional<Cart> findByProductId(Long productId);

}
