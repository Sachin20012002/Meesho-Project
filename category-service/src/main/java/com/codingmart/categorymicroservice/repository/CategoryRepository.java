package com.codingmart.categorymicroservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.codingmart.categorymicroservice.entity.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

	
	

	//get all sub and child category in specfic category
	@Query("select u from Category u where u.id=:x" )
	public Category getActiveCategoryById(@Param("x") long id);

	public Category findByName(String name);


}
