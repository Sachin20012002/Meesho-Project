package com.codingmart.productmicroservice.repository;

import com.codingmart.productmicroservice.entity.Size;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SizeRepository extends JpaRepository<Size,Long> {
}
