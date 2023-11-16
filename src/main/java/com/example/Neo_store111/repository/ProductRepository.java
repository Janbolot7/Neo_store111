package com.example.Neo_store111.repository;

import com.example.Neo_store111.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    Optional<Product> findAllByProductId(Long id);
    Optional<Product> findAllByProductName(String productName);
}
