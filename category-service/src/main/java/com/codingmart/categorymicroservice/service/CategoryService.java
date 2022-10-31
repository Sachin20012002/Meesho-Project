package com.codingmart.categorymicroservice.service;

import java.util.List;
import java.util.Objects;

import com.codingmart.categorymicroservice.entity.Category;
import com.codingmart.categorymicroservice.entity.SubCategory;
import com.codingmart.categorymicroservice.repository.CategoryRepository;
import com.codingmart.categorymicroservice.repository.SubCategoryRepository;
import com.codingmart.categorymicroservice.response.ApiResponse;
import com.codingmart.categorymicroservice.response.IdNotFound;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class CategoryService {

	private CategoryRepository categoryRepo;
	private SubCategoryRepository subCategoryRepo;
	private ApiResponse apiResponse;
	
	public CategoryService(CategoryRepository categoryRepo,ApiResponse apiResponse,SubCategoryRepository subCategoryRepo) {
		this.categoryRepo=categoryRepo;
		this.apiResponse=apiResponse;
		this.subCategoryRepo=subCategoryRepo;
	}
	

	public ApiResponse saveCategory(Category category) {
		Category category1=categoryRepo.findByName(category.getName());
		if(Objects.isNull(category1)) {
		Category category2 =categoryRepo.save(category);
		apiResponse.setData(category2);
		apiResponse.setStatus(HttpStatus.OK.value());
		apiResponse.setError(null);
		}
		else {
			apiResponse.setStatus(HttpStatus.OK.value());
			apiResponse.setData(null);
			apiResponse.setError("id already exist");
		}	
		return apiResponse;
	}
	
	public ApiResponse saveAllCategory(List<Category> categories){
		for(Category category : categories)
		{
			Category category1=categoryRepo.findByName(category.getName());
			if(Objects.isNull(category1)) {
				categoryRepo.save(category);
			}
			else {
				continue;
			}
		}
		apiResponse.setData(categories);
		apiResponse.setStatus(HttpStatus.OK.value());
		apiResponse.setError(null);
		return apiResponse;
	}

	public ApiResponse getAllCategories() {
		List<Category> categories=categoryRepo.findAll();
		 if(categories.isEmpty()) {
			 throw new IdNotFound("no Categories Present");
		 }
		apiResponse.setData(categories);
		apiResponse.setStatus(HttpStatus.OK.value());
		apiResponse.setError(null);
		return apiResponse;
	}
	
	public ApiResponse getCategoryById(long id) {
		if(categoryRepo.findById(id).isEmpty()) {
			 throw new IdNotFound("Category Id not Found");
		}
		//categoryRepo.findById(id).get();
		apiResponse.setData(categoryRepo.findById(id).get());
		apiResponse.setStatus(HttpStatus.OK.value());
		apiResponse.setError(null);
		return apiResponse;
	}
	
//	public ApiResponse getCategoryByName(String name) {
	//categoryRepo.findByName(name);
	//apiResponse.setData(categoryRepo.findByName(name));
	//apiResponse.setStatus(HttpStatus.OK.value());
	//apiResponse.setError(null);
	
//		return apiResponse;
//	}
	 
	public ApiResponse UpdateCategory(Category category,Long id) {
		 if(categoryRepo.findById(id).isEmpty()){
	            throw new IdNotFound("Category Id not Found to update");
	       }
		Category exist=categoryRepo.findById(id).get();
		exist.setName(category.getName());
		exist.setActive(category.isActive());	
		apiResponse.setData(categoryRepo.save(exist));
		apiResponse.setStatus(HttpStatus.OK.value());
		apiResponse.setError(null);
		return apiResponse;
	}

	public ApiResponse deleteCategory(long id) {
		if(categoryRepo.findById(id).isEmpty()) {
			 throw new IdNotFound("Category Id not Found");
		}
		categoryRepo.deleteById(id);
		String msg="Id "+ id +" Deleted Successfully";
		apiResponse.setData(msg);
		apiResponse.setStatus(HttpStatus.OK.value());
		apiResponse.setError(null);
		return apiResponse;
	}
	
	
     public ApiResponse getActiveCategoryById(Long id){
    	 if(categoryRepo.findById(id).isEmpty()) {
			 throw new IdNotFound("Category Id not Found");
		}
    	// Category cy=categoryRepo.getActiveCategoryById(id);
    	 boolean status=categoryRepo.getActiveCategoryById(id).isActive();
    	if(status==true) {
    		apiResponse.setData(categoryRepo.getActiveCategoryById(id));
    		apiResponse.setStatus(HttpStatus.OK.value());
    		apiResponse.setError(null);
    		 }
    	 else {
    		 apiResponse.setData(null);
    		 apiResponse.setStatus(HttpStatus.OK.value());
    		 apiResponse.setError("category is inactive ");
    		 }
    	 return apiResponse;
     }

	public ApiResponse saveSubCategoryForCategory(SubCategory subCategory, long id) {
		Category category = categoryRepo.findById(id).get();
		category.getSubCategory().add(subCategoryRepo.save(subCategory));
		apiResponse.setData(categoryRepo.save(category));
		apiResponse.setError(null);
		apiResponse.setStatus(HttpStatus.OK.value());
		return  apiResponse;
	}
}
