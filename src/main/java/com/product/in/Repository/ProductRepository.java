package com.product.in.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.product.in.entity.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
