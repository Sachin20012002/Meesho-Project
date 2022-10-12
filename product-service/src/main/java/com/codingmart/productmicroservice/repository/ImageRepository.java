package com.codingmart.productmicroservice.repository;

import com.codingmart.productmicroservice.entity.Image;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ImageRepository extends JpaRepository<Image,Long> {
}
