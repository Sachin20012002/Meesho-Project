package com.example.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.example.entity.SubCategory;
import com.example.repository.SubCategoryRepository;
import com.example.response.ApiResponse;

@Service
public class SubCategoryService {

	private SubCategoryRepository subCategoryRepo;
	private ApiResponse apiResponse;
	
	public SubCategoryService(SubCategoryRepository subCategoryRepo,ApiResponse apiResponse) {
		this.subCategoryRepo=subCategoryRepo;
		this.apiResponse=apiResponse;
	}
	
	public ApiResponse saveSubCategory(SubCategory subCategory) {
		SubCategory subCategory1=subCategoryRepo.findByName(subCategory.getName());
		if(Objects.isNull(subCategory1)) {
		subCategoryRepo.save(subCategory);
		apiResponse.setData(subCategory);
		apiResponse.setStatus(HttpStatus.OK.value());
		apiResponse.setError(null);
		}
		else {
			apiResponse.setData(null);
			apiResponse.setStatus(HttpStatus.OK.value());
			apiResponse.setError("id already exist");
		}
		return apiResponse;
	}
	
	public ApiResponse saveAllSubCategory(List<SubCategory> subCategory){
		subCategoryRepo.saveAll(subCategory);
		apiResponse.setData(subCategory);
		apiResponse.setStatus(HttpStatus.OK.value());
		apiResponse.setError(null);
		return apiResponse;
	}
	public ApiResponse getAllSubCategory() {
		apiResponse.setData(subCategoryRepo.findAll());
		apiResponse.setStatus(HttpStatus.OK.value());
		apiResponse.setError(null);
		return apiResponse;
	}
	public ApiResponse gesubCategoryById(long id) {
		SubCategory s=subCategoryRepo.findById(id).get();
		apiResponse.setData(s);
		apiResponse.setStatus(HttpStatus.OK.value());
		apiResponse.setError(null);
		return apiResponse;
	}
//	public ApiResponse getsubCategoryByName(String name) {
//	 SubCategorys s= subCategoryRepo.findByName(name);
//	    apiResponse.setData(s);
//	apiResponse.setStatus(HttpStatus.OK.value());
//	apiResponse.setError(null);
//		return apiResponse;
//	}
	public ApiResponse updateSubCategory(SubCategory subCategory) {
		
		SubCategory existing=subCategoryRepo.findById(subCategory.getId()).get();
		existing.setName(subCategory.getName());
		existing.setActive(subCategory.isActive());
		apiResponse.setData(subCategoryRepo.save(existing));
		apiResponse.setStatus(HttpStatus.OK.value());
		apiResponse.setError(null);
		   return apiResponse;
	}
	public ApiResponse deleteSubCategory(long id) {
		 subCategoryRepo.deleteById(id);
		 String mes=" Id "+ id +" Deleted Successfully";
		 apiResponse.setData(mes);
		 apiResponse.setStatus(HttpStatus.OK.value());
			apiResponse.setError(null);
		 return apiResponse;
	}
	
	//fetching all data 
	
	public ApiResponse getAllSubCategoriesFromCategoryId(long id){
		List<SubCategory> sb=subCategoryRepo.getAllSubCategoriesFromCategoryId(id);
			apiResponse.setData(sb);
			apiResponse.setStatus(HttpStatus.OK.value());
			apiResponse.setError(null);
		
		return apiResponse;
		
	}
}
