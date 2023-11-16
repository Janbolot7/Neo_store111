package com.example.Neo_store111.service;

import com.example.Neo_store111.dto.ProductInfo;
import com.example.Neo_store111.exception.NotFoundException;
import com.example.Neo_store111.model.Product;
import com.example.Neo_store111.repository.ProductRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
    private final ProductRepository productRepository;
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }
    private int allQuantity = 0;

    public Product checkProductOnExistAndReturn(Long id) {
        return productRepository.findAllByProductId(id).orElseThrow(
                () -> new NotFoundException("Product was not found!"));
    }

    public Product parseToProduct(ProductInfo productInfo) {
        return Product.builder().productName(productInfo.getProductName()).
                productPrice(productInfo.getProductPrice()).
                description(productInfo.getDescription())
                .quantity(productInfo.getQuantity())
                .typeOfProduct(productInfo.getTypeOfProduct())
                .build();
    }
    public List<Product> findAllProducts() {
        return productRepository.findAll();
    }

    public ResponseEntity<Object> createProduct(ProductInfo productInfo) {
        ResultMod resultMod = new ResultMod();

        if (productRepository.findAllByProductName(productInfo.getProductName()).isPresent()) {
            resultMod.setResult("Product already exist!");
            return ResponseEntity.ok(resultMod.getResult());
        }
        productRepository.save(parseToProduct(productInfo));
        resultMod.setResult("Product was created!");
        allQuantity += productInfo.getQuantity();
        return ResponseEntity.ok(resultMod.getResult());
    }

    public ProductInfo getProductById(Long id) {
        Product product = checkProductOnExistAndReturn(id);
        return ProductInfo.builder().productName(product.getProductName())
                .productPrice(product.getProductPrice())
                .description(product.getDescription())
                .quantity(product.getQuantity())
                .typeOfProduct(product.getTypeOfProduct())
                .build();
    }

    public ResponseEntity<Object> updateProductById(Long id, ProductInfo productInfo) {
        ResultMod resultMod = new ResultMod();
        Product product = checkProductOnExistAndReturn(id);

        product.setProductName(productInfo.getProductName());
        product.setProductPrice(productInfo.getProductPrice());
        product.setDescription(productInfo.getDescription());
        product.setTypeOfProduct(product.getTypeOfProduct());
        product.setQuantity(productInfo.getQuantity());

        productRepository.save(product);
        resultMod.setResult("Product was updated!");

        return ResponseEntity.ok(resultMod.getResult());
    }

    public ResponseEntity<Object> deleteProductById(Long id) {
        ResultMod resultMod = new ResultMod();

        productRepository.deleteById(id);
        resultMod.setResult("Product was deleted!");

        return ResponseEntity.ok(resultMod.getResult());
    }

    public int getAllQuantity() {
        return allQuantity;
    }
}
