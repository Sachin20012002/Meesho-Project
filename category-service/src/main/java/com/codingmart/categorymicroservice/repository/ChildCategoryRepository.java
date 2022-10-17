package com.codingmart.categorymicroservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.codingmart.categorymicroservice.entity.ChildCategory;

public interface ChildCategoryRepository extends JpaRepository<ChildCategory, Long> {

	ChildCategory findByName(String name);

}
