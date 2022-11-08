package com.codingmart.categorymicroservice.repository;

import com.codingmart.categorymicroservice.entity.SubCategory;
import org.springframework.data.jpa.repository.JpaRepository;

import com.codingmart.categorymicroservice.entity.ChildCategory;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ChildCategoryRepository extends JpaRepository<ChildCategory, Long> {

	ChildCategory findByName(String name);

    @Query(value="select * from child_category where sub_category_id =:id",nativeQuery=true)
    List<ChildCategory> getAllChildCategoriesFromSubCategoryId(@Param("id") long id);
}
