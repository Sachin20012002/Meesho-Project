package com.example.service;

import java.util.List;
import java.util.Objects;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.example.entity.Category;
import com.example.repository.CategoryRepository;
import com.example.response.ApiResponse;

@Service
public class CategoryService {

	private CategoryRepository categoryRepo;
	private ApiResponse apiResponse;
	
	public CategoryService(CategoryRepository categoryRepo,ApiResponse apiResponse) {
		this.categoryRepo=categoryRepo;
		this.apiResponse=apiResponse;
	}
	

	public ApiResponse saveCategory(Category category) {
		Category category1=categoryRepo.findByName(category.getName());
		if(Objects.isNull(category1)) {
		categoryRepo.save(category);
		apiResponse.setData(category);
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
	
	public ApiResponse saveAllCategory(List<Category> category){
		categoryRepo.saveAll(category);
		apiResponse.setData(category);
		apiResponse.setStatus(HttpStatus.OK.value());
		apiResponse.setError(null);
		return apiResponse;
	}

	public ApiResponse getAllCategories() {
		apiResponse.setData(categoryRepo.findAll());
		apiResponse.setStatus(HttpStatus.OK.value());
		apiResponse.setError(null);
		return apiResponse;
	}
	
	public ApiResponse getCategoryById(long id) {
		
		Category c = categoryRepo.findById(id).get();
		apiResponse.setData(c);
		apiResponse.setStatus(HttpStatus.OK.value());
		apiResponse.setError(null);
		return apiResponse;
	}
	
//	public ApiResponse getCategoryByName(String name) {
	//Category c=categoryRepo.findByName(name);
	//apiResponse.setData(c);
	//apiResponse.setStatus(HttpStatus.OK.value());
	//apiResponse.setError(null);
	
//		return apiResponse;
//	}
	 
	public ApiResponse UpdateCategory(Category category) {
		Category exist=categoryRepo.findById(category.getId()).get();
		exist.setName(category.getName());
		exist.setActive(category.isActive());	
		apiResponse.setData(categoryRepo.save(exist));
		apiResponse.setStatus(HttpStatus.OK.value());
		apiResponse.setError(null);
		return apiResponse;
	}

	public ApiResponse deleteCategory(long id) {
		categoryRepo.deleteById(id);
		String msg="Id "+ id +" Deleted Successfully";
		apiResponse.setData(msg);
		apiResponse.setStatus(HttpStatus.OK.value());
		apiResponse.setError(null);
		return apiResponse;
	}
	
	
     public ApiResponse getActiveCategoryById(Long id){
    	 Category cy=categoryRepo.getActiveCategoryById(id);
    	 boolean status=cy.isActive();
    	if(status==true) {
    		apiResponse.setData(cy);
    		apiResponse.setStatus(HttpStatus.OK.value());
    		apiResponse.setError(null);
    		 }
    	 else {
    		 apiResponse.setData(null);
    		 apiResponse.setStatus(HttpStatus.OK.value());
    		 apiResponse.setError("category is not active ");
    		 }
    	 return apiResponse;
     }
}