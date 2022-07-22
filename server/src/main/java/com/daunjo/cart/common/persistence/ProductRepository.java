package com.daunjo.cart.common.persistence;

import com.daunjo.cart.common.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
