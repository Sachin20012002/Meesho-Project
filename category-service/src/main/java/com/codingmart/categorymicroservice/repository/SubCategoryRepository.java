package com.codingmart.categorymicroservice.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.codingmart.categorymicroservice.entity.SubCategory;

public interface SubCategoryRepository extends JpaRepository<SubCategory, Long> {

	@Query(value="select * from sub_category where category_id =:id",nativeQuery=true)
	// get all childCategory inside this subcategory
	 List<SubCategory> getAllSubCategoriesFromCategoryId(@Param("id") long id);

	
	 SubCategory findByName(String name);
}
