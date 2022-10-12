package com.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.entity.ChildCategory;
import com.example.entity.SubCategory;

public interface ChildCategoryRepository extends JpaRepository<ChildCategory, Long> {

	ChildCategory findByName(String name);

}
