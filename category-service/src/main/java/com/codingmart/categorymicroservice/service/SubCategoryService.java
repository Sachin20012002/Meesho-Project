package com.codingmart.categorymicroservice.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import com.codingmart.categorymicroservice.entity.Category;
import com.codingmart.categorymicroservice.entity.ChildCategory;
import com.codingmart.categorymicroservice.entity.SubCategory;
import com.codingmart.categorymicroservice.repository.CategoryRepository;
import com.codingmart.categorymicroservice.repository.ChildCategoryRepository;
import com.codingmart.categorymicroservice.repository.SubCategoryRepository;
import com.codingmart.categorymicroservice.response.ApiResponse;
import com.codingmart.categorymicroservice.response.IdNotFound;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class SubCategoryService {

	private SubCategoryRepository subCategoryRepo;
	private ApiResponse apiResponse;
	private ChildCategoryRepository childCategoryRepo;


	private Category category;
	
	public SubCategoryService(SubCategoryRepository subCategoryRepo,ApiResponse apiResponse,ChildCategoryRepository childCategoryRepo) {
		this.subCategoryRepo=subCategoryRepo;
		this.apiResponse=apiResponse;
		this.childCategoryRepo=childCategoryRepo;
	}
	
	public ApiResponse saveSubCategory(SubCategory subCategory) {

		SubCategory subCategory1=subCategoryRepo.findByName(subCategory.getName());
		if(Objects.isNull(subCategory1)) {

			SubCategory subCategory2=subCategoryRepo.save(subCategory);
			apiResponse.setData(subCategory2);
			apiResponse.setStatus(HttpStatus.OK.value());
			apiResponse.setError(null);

		}
		else {
			apiResponse.setData(null);
			apiResponse.setStatus(HttpStatus.OK.value());
			apiResponse.setError("id already exist");
		}
		System.out.println(apiResponse.getData());
			return  apiResponse;
	}
	
	public ApiResponse saveAllSubCategory(List<SubCategory> subCategories){
		for(SubCategory subcategory : subCategories)
		{
			SubCategory subCategory1=subCategoryRepo.findByName(subcategory.getName());
			if(Objects.isNull(subCategory1)) {
				subCategoryRepo.save(subcategory);
			}
			else {
				continue;
			}
		}
		//subCategoryRepo.saveAll(subCategories);
		apiResponse.setData(subCategories);
		apiResponse.setStatus(HttpStatus.OK.value());
		apiResponse.setError(null);
		return apiResponse;
	}
	public ApiResponse getAllSubCategory() {
		List<SubCategory> subCategories=subCategoryRepo.findAll();
		if(subCategories.isEmpty()) {
			throw new IdNotFound("no Subategories Present");
		}
		apiResponse.setData(subCategories);
		apiResponse.setStatus(HttpStatus.OK.value());
		apiResponse.setError(null);
		return apiResponse;
	}
	public ApiResponse getSubCategoryById(long id) {
		if(subCategoryRepo.findById(id).isEmpty()) {
			 throw new IdNotFound("Product Id not Found");
		}
		subCategoryRepo.findById(id).get();
		apiResponse.setData(subCategoryRepo.findById(id).get());
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
	public ApiResponse updateSubCategory(SubCategory subCategory,Long id) {
		if(subCategoryRepo.findById(id).isEmpty()) {
			throw new IdNotFound("Id not found to update");
		}
		SubCategory existing=subCategoryRepo.findById(id).get();
		existing.setName(subCategory.getName());
		existing.setActive(subCategory.isActive());
		apiResponse.setData(subCategoryRepo.save(existing));
		apiResponse.setStatus(HttpStatus.OK.value());
		apiResponse.setError(null);
		   return apiResponse;
	}
	public ApiResponse deleteSubCategory(long id) {
		if(subCategoryRepo.findById(id).isEmpty()) {
			 throw new IdNotFound("Product Id not Found");
		}
		 subCategoryRepo.deleteById(id);
		 String mes=" Id "+ id +" Deleted Successfully";
		 apiResponse.setData(mes);
		 apiResponse.setStatus(HttpStatus.OK.value());
			apiResponse.setError(null);
		 return apiResponse;
	}
	
	//fetching all data 
	
	public ApiResponse getAllSubCategoriesFromCategoryId(long id){
		if(subCategoryRepo.getAllSubCategoriesFromCategoryId(id).isEmpty()) {
			 throw new IdNotFound("No SubCategory found for the given Category Id");
		}
		List<SubCategory> sb=subCategoryRepo.getAllSubCategoriesFromCategoryId(id);
			apiResponse.setData(sb);
			apiResponse.setStatus(HttpStatus.OK.value());
			apiResponse.setError(null);
		
		return apiResponse;
	}

	public ApiResponse saveChildCategoryForSubcategory(ChildCategory childCategory, long id) {
		SubCategory subCategory = subCategoryRepo.findById(id).get();
		subCategory.getChildCategory().add(childCategoryRepo.save(childCategory));
//		System.out.println(subCategory);
		apiResponse.setData(subCategoryRepo.save(subCategory));
		apiResponse.setError(null);
		apiResponse.setStatus(HttpStatus.OK.value());
		return  apiResponse;
	}
}
