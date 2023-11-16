package com.example.Neo_store111.service;

import com.example.Neo_store111.dto.TypeOfProductInfo;
import com.example.Neo_store111.exception.NotFoundException;
import com.example.Neo_store111.model.TypeOfProduct;
import com.example.Neo_store111.repository.TypeOfProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@AllArgsConstructor
public class TypeOfProductService {
    private final TypeOfProductRepository typeOfProductRepository;

    public TypeOfProduct checkTypeOnExistAndReturn(Long id) {
        return typeOfProductRepository.findAllByCategoryId(id).orElseThrow(
                () -> new NotFoundException("Category was not found!"));
    }

    public TypeOfProduct parsingToType(TypeOfProductInfo typeOfProductInfo) {
        return TypeOfProduct.builder().typeName(typeOfProductInfo.getTypeName()).build();
    }

    public List<TypeOfProduct> getAllCategories() {
        return typeOfProductRepository.findAll();
    }

    public ResponseEntity<Object> createTypeOfProduct(TypeOfProductInfo categoryInfo) {
        ResultMod resultMod = new ResultMod();
        if (typeOfProductRepository.findAllByCategoryName(categoryInfo.getTypeName()).isPresent()) {
            resultMod.setResult("Category already exist!");
            return ResponseEntity.ok(resultMod.getResult());
        }
        typeOfProductRepository.save(parsingToType(categoryInfo));
        resultMod.setResult("Category was created!");

        return ResponseEntity.ok(resultMod.getResult());
    }

    public TypeOfProductInfo getTypeOfProductById(Long id) {
        TypeOfProduct category = checkTypeOnExistAndReturn(id);
        return TypeOfProductInfo.builder().typeName(category.getTypeName()).build();
    }

    public ResponseEntity<Object> updateTypeOfProductById(Long id, TypeOfProductInfo typeOfProductRequest) {
        TypeOfProduct typeOfProduct= checkTypeOnExistAndReturn(id);
        ResultMod resultMod = new ResultMod();
        typeOfProduct.setTypeName(typeOfProductRequest.getTypeName());
        typeOfProductRepository.save(typeOfProduct);
        resultMod.setResult("Category was updated!");

        return ResponseEntity.ok(resultMod.getResult());
    }

    public ResponseEntity<Object> deleteTypeOfProductById(Long id) {
        ResultMod resultMod = new ResultMod();
        typeOfProductRepository.deleteById(id);
        resultMod.setResult("User was deleted!");

        return ResponseEntity.ok(resultMod.getResult());
    }
}
