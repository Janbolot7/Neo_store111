package com.example.Neo_store111.controller;

import com.example.Neo_store111.dto.ProductInfo;
import com.example.Neo_store111.model.Product;
import com.example.Neo_store111.service.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/vv/products")
public class ProductController {
    private final ProductService productService;

    @GetMapping("/allProducts")
    public List<Product> findAllProducts() {
        return productService.findAllProducts();
    }

    @PostMapping("/createProduct")
    public ResponseEntity<Object> createProduct(ProductInfo productInfo) {
        return productService.createProduct(productInfo);
    }

    @GetMapping("{id}")
    public ProductInfo findProductById(@PathVariable Long id) {
        return productService.getProductById(id);
    }

    @PutMapping("{id}")
    public ResponseEntity<Object> updateProductById(@PathVariable Long id, ProductInfo productRequest) {
        return productService.updateProductById(id, productRequest);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Object> deleteProductById(@PathVariable Long id) {
        return productService.deleteProductById(id);
    }
}