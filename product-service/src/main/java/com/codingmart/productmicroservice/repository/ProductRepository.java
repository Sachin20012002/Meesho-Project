package com.codingmart.productmicroservice.repository;

import com.codingmart.productmicroservice.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product,Long> {
    Product findByName(String name);

    List<Product> findAllByChildCategoryId(Long id);

    List<Product> findAllByTypeId(Long id);

    List<Product> findAllByBrandId(Long id);

    List<Product> findAllByColor(String color);
}
