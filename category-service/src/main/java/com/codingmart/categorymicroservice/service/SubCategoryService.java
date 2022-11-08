package com.codingmart.categorymicroservice.service;


import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import com.codingmart.categorymicroservice.entity.ChildCategory;
import com.codingmart.categorymicroservice.entity.SubCategory;
import com.codingmart.categorymicroservice.repository.ChildCategoryRepository;
import com.codingmart.categorymicroservice.repository.SubCategoryRepository;
import com.codingmart.categorymicroservice.response.ApiResponse;
import com.codingmart.categorymicroservice.response.ErrorResponse;
import com.codingmart.categorymicroservice.response.IdNotFound;
import com.codingmart.productmicroservice.entity.Product;
import org.springframework.stereotype.Service;

@Service
public class SubCategoryService
{

	private final SubCategoryRepository subCategoryRepo;
	private final ApiResponse apiResponse;
	private final ChildCategoryRepository childCategoryRepo;
	private final ChildCategoryService childCategoryService;

	
	public SubCategoryService(SubCategoryRepository subCategoryRepo, ApiResponse apiResponse, ChildCategoryRepository childCategoryRepo, ChildCategoryService childCategoryService) {
		this.subCategoryRepo=subCategoryRepo;
		this.apiResponse=apiResponse;
		this.childCategoryRepo=childCategoryRepo;
		this.childCategoryService = childCategoryService;
	}
	
	public ApiResponse saveSubCategory(SubCategory subCategory) {
		apiResponse.resetResponse();
		if(Objects.isNull(subCategoryRepo.findByName(subCategory.getName()))) {
			apiResponse.setData(subCategoryRepo.save(subCategory));
		}
		else {
			apiResponse.setError(new ErrorResponse("SubCategory already exist","Avoid Duplicate entry"));

		}
		return  apiResponse;
	}
	
	public ApiResponse saveAllSubCategory(List<SubCategory> subCategories){
		apiResponse.resetResponse();
		for(SubCategory subcategory : subCategories) {
			if(Objects.isNull(subCategoryRepo.findByName(subcategory.getName()))) {
				apiResponse.setData(subCategoryRepo.save(subcategory));
			}
		}
		return apiResponse;
	}
	public ApiResponse getAllSubCategory() {
		apiResponse.resetResponse();
		List<SubCategory> subCategories=subCategoryRepo.findAll();
		if(subCategories.isEmpty()) {
			throw new IdNotFound("No SubCategories Exist");
		}
		apiResponse.setData(subCategories);
		return apiResponse;
	}
	public ApiResponse getSubCategoryById(long id) {
		apiResponse.resetResponse();
		if(subCategoryRepo.findById(id).isEmpty()) {
			 throw new IdNotFound("SubCategories not Found");
		}
		apiResponse.setData(subCategoryRepo.findById(id).get());

		return apiResponse;
	}
	public ApiResponse getSubCategoryByName(String name) {
		apiResponse.resetResponse();
	    apiResponse.setData(subCategoryRepo.findByName(name));
		return apiResponse;
	}
	public ApiResponse updateSubCategory(SubCategory subCategory,Long id) {
		apiResponse.resetResponse();
		if(subCategoryRepo.findById(id).isEmpty()) {
			throw new IdNotFound("SubCategory not found to update");
		}
		SubCategory existing=subCategoryRepo.findById(id).get();
		existing.setName(subCategory.getName());
		existing.setActive(subCategory.isActive());
		apiResponse.setData(subCategoryRepo.save(existing));
		   return apiResponse;
	}
	public ApiResponse deleteSubCategory(long id) {
		apiResponse.resetResponse();
		if(subCategoryRepo.findById(id).isEmpty()) {
			 throw new IdNotFound("SubCategory not Found");
		}
		 subCategoryRepo.deleteById(id);
		 apiResponse.setData("Id "+ id +" Deleted Successfully");
		 return apiResponse;
	}
	
	//fetching all data 
	
	public ApiResponse getAllSubCategoriesFromCategoryId(long id){
		apiResponse.resetResponse();
		if(subCategoryRepo.getAllSubCategoriesFromCategoryId(id).isEmpty()) {
			 throw new IdNotFound("No SubCategory found for the given Category Id");
		}
		List<SubCategory> subCategoryList=subCategoryRepo.getAllSubCategoriesFromCategoryId(id);
			apiResponse.setData(subCategoryList);

		return apiResponse;
	}

	public ApiResponse saveChildCategoryForSubcategory(ChildCategory childCategory, long id) {
		apiResponse.resetResponse();
		if(subCategoryRepo.findById(id).isPresent()) {
			SubCategory subCategory = subCategoryRepo.findById(id).get();
			subCategory.getChildCategory().add(childCategoryRepo.save(childCategory));
			apiResponse.setData(subCategoryRepo.save(subCategory));
		}
		else{
			throw new  IdNotFound("Id not exist");
		}
		return  apiResponse;
	}

	public ApiResponse getAllProductsFromSubCategoryId(long id) {
		List<ChildCategory> childCategories=((SubCategory)getSubCategoryById(id).getData()).getChildCategory();
		List<Product> products=new ArrayList<>();
		for (ChildCategory childCategory:childCategories){
			products.add((Product) childCategoryService.getAllProductsByChildCategoryId(childCategory.getId()).getData());
		}
		apiResponse.resetResponse();
		apiResponse.setData(products);
		return apiResponse;
	}
}
