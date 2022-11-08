package com.codingmart.categorymicroservice.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import com.codingmart.categorymicroservice.entity.Category;
import com.codingmart.categorymicroservice.entity.SubCategory;
import com.codingmart.categorymicroservice.repository.CategoryRepository;
import com.codingmart.categorymicroservice.repository.SubCategoryRepository;
import com.codingmart.categorymicroservice.response.ApiResponse;
import com.codingmart.categorymicroservice.response.ErrorResponse;
import com.codingmart.categorymicroservice.response.IdNotFound;
import org.springframework.stereotype.Service;

@Service
public class CategoryService {

	private final CategoryRepository categoryRepo;
	private final SubCategoryRepository subCategoryRepo;
	private final ApiResponse apiResponse;
	
	public CategoryService(CategoryRepository categoryRepo,ApiResponse apiResponse,SubCategoryRepository subCategoryRepo) {
		this.categoryRepo=categoryRepo;
		this.apiResponse=apiResponse;
		this.subCategoryRepo=subCategoryRepo;
	}
	

	public ApiResponse saveCategory(Category category) {
		apiResponse.resetResponse();
		if(Objects.isNull(categoryRepo.findByName(category.getName()))) {
			apiResponse.setData(categoryRepo.save(category));
		}
		else {

			apiResponse.setError(new ErrorResponse("Category already exist","Avoid Duplicate entry"));
		}
		return apiResponse;
	}

	
	public ApiResponse saveAllCategory(List<Category> categories){
		apiResponse.resetResponse();
		for(Category category : categories)
		{
			if(Objects.isNull(categoryRepo.findByName(category.getName())))
				apiResponse.setData(categoryRepo.save(category));
		}
		return apiResponse;
	}

	public ApiResponse getAllCategories() {
		apiResponse.resetResponse();
		List<Category> categories=categoryRepo.findAll();
		 if(categories.isEmpty())
			 throw new IdNotFound("no Categories Present");
		apiResponse.setData(categories);
		return apiResponse;
	}
	
	public ApiResponse getCategoryById(long id) {
		apiResponse.resetResponse();
		if(categoryRepo.findById(id).isEmpty())
			 throw new IdNotFound("Category Id not Found");
		apiResponse.setData(categoryRepo.findById(id).get());
		return apiResponse;
	}
	
	public ApiResponse getCategoryByName(String name) {
		apiResponse.resetResponse();
		apiResponse.setData(categoryRepo.findByName(name));
		return apiResponse;
	}

	public ApiResponse UpdateCategory(Category category,Long id) {
		apiResponse.resetResponse();
		 if(categoryRepo.findById(id).isEmpty()){
	            throw new IdNotFound("Category Id not Found to update");
	       }
		Category exist=categoryRepo.findById(id).get();
		exist.setName(category.getName());
		exist.setActive(category.isActive());	
		apiResponse.setData(categoryRepo.save(exist));
		return apiResponse;
	}

	public ApiResponse deleteCategory(long id) {
		apiResponse.resetResponse();
		if(categoryRepo.findById(id).isEmpty()) {
			 throw new IdNotFound("Category Id not Found");
		}
		categoryRepo.deleteById(id);
		apiResponse.setData("Id "+ id +" Deleted Successfully");
		return apiResponse;
	}
	
	
//     public ApiResponse getActiveCategoryById(Long id){
//		 apiResponse.resetResponse();
//    	 if(categoryRepo.findById(id).isEmpty()) {
//			 throw new IdNotFound("Category Id not Found");
//		 }
//    	 boolean status=categoryRepo.getActiveCategoryById(id).isActive();
//    	if(status) {
//			apiResponse.setData(categoryRepo.getActiveCategoryById(id));
//		}
//    	 else {
//			apiResponse.setError(new ErrorResponse("Category already exist","Avoid Duplicate entry"));
//		}
//    	 return apiResponse;
//     }

	public ApiResponse saveSubCategoryForCategory(SubCategory subCategory, long id) {
		apiResponse.resetResponse();
		if(categoryRepo.findById(id).isPresent()) {
			Category category = categoryRepo.findById(id).get();
			category.getSubCategory().add(subCategoryRepo.save(subCategory));
			apiResponse.setData(categoryRepo.save(category));
			}
		else
			throw new IdNotFound("Category Id not exist");
		return  apiResponse;
	}

	public ApiResponse getAllActiveCategories() {
		apiResponse.resetResponse();
		List<Category> categories=categoryRepo.findAll();
		List<Category> activeCategories=categories.stream().filter((category)-> category.isActive()).collect(Collectors.toList());
		if(categories.isEmpty())
			throw new IdNotFound("no Categories Present");
		apiResponse.setData(activeCategories);
		return apiResponse;
	}

}
