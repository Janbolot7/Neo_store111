package com.example.Neo_store111.controller;

import com.example.Neo_store111.dto.TypeOfProductInfo;
import com.example.Neo_store111.model.TypeOfProduct;
import com.example.Neo_store111.service.TypeOfProductService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/api/typeOfProduct")//v1
@AllArgsConstructor
public class TypeOfProductController {
    private final TypeOfProductService categoryService;

    @GetMapping("/allCategories")
    public List<TypeOfProduct> getAllTypeOfProduct() {
        return categoryService.getAllCategories();
    }

    @PostMapping("/createCategory")
    public ResponseEntity<Object> createCategory(TypeOfProductInfo typeOfProductRequest) {
        return categoryService.createTypeOfProduct(typeOfProductRequest);
    }

    @GetMapping("{id}")
    public TypeOfProductInfo getTypeOfProductById(@PathVariable Long id) {
        return categoryService.getTypeOfProductById(id);
    }

    @PutMapping("{id}")
    public ResponseEntity<Object> updateTypeOfProductById
            (@PathVariable Long id, TypeOfProductInfo typeOfProductRequest) {
        return categoryService.updateTypeOfProductById(id, typeOfProductRequest);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Object> deleteTypeOfProductById(@PathVariable Long id) {
        return categoryService.deleteTypeOfProductById(id);
    }
}