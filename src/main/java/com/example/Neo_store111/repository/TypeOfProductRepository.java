package com.example.Neo_store111.repository;

import com.example.Neo_store111.model.TypeOfProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface TypeOfProductRepository extends JpaRepository<TypeOfProduct, Long> {
        Optional<TypeOfProduct> findAllByTypeName(String typeName);
        //Optional<TypeOfProduct> findCategoryByCategoryId(Long typeId);
        Optional<TypeOfProduct> findAllByTypeId(Long typeId);
        //@Query(add script)
        //void deleteCategoryByCategoryName(String typeName);
}