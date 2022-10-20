package com.codingmart.productmicroservice.repository;

import com.codingmart.productmicroservice.entity.ProductDetail;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductDetailRepository extends JpaRepository<ProductDetail,Long> {
}
