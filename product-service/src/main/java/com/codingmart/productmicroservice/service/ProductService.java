package com.codingmart.productmicroservice.service;


import com.codingmart.productmicroservice.entity.Product;

import java.util.List;

public interface ProductService {
    Product getProductById(Long id);

    Product getProductByName(String name);

    Product addProduct(Product product);

    String deleteProduct(Long id);

    Product updateProduct(Long id, Product product);

    List<Product> getAllProductsByChildCategoryId(Long id);

    List<Product> getAllProductsByTypeId(Long id);

    List<Product> getAllProductsByBrandId(Long id);

    List<Product> getAllProducts();

    List<Product> getAllProductsByColor(String color);
}
