package com.codingmart.categorymicroservice.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import com.codingmart.categorymicroservice.entity.Category;
import com.codingmart.categorymicroservice.entity.SubCategory;
import com.codingmart.categorymicroservice.repository.ChildCategoryRepository;
import com.codingmart.categorymicroservice.repository.SubCategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.codingmart.categorymicroservice.entity.ChildCategory;
import com.codingmart.categorymicroservice.response.ApiResponse;
import com.codingmart.categorymicroservice.response.IdNotFound;

@Service
public class ChildCategoryService {
	

		private ChildCategoryRepository childCategoryRepo;
		private ApiResponse apiResponse;



	    @Autowired
		private  SubCategoryRepository subCategoryRepo;
		public ChildCategoryService(ChildCategoryRepository childCategoryRepo,ApiResponse apiResponse) {
			this.childCategoryRepo=childCategoryRepo;
			this.apiResponse=apiResponse;
		}
		
		public ApiResponse saveChildCategory(ChildCategory childCategory) {
			ChildCategory childCategory1=childCategoryRepo.findByName(childCategory.getName());
			if(Objects.isNull(childCategory1)) {
				ChildCategory childCategory2=childCategoryRepo.save(childCategory);
			    apiResponse.setData(childCategory2);
				apiResponse.setStatus(HttpStatus.OK.value());
				apiResponse.setError(null);
			}
			else {
				apiResponse.setStatus(HttpStatus.OK.value());
				apiResponse.setData(null);
				apiResponse.setError("id already present");
			}
			return apiResponse;
		}
		
		public ApiResponse saveAllChildCategory(List<ChildCategory> childCategories){
			for(ChildCategory childCategory : childCategories)
			{
				ChildCategory childCategory1=childCategoryRepo.findByName(childCategory.getName());
				if(Objects.isNull(childCategory1)) {
					childCategoryRepo.save(childCategory);
				}
				else {
					continue;
				}
			}
			//childCategoryRepo.saveAll(childCategories);
			apiResponse.setData(childCategories);
			apiResponse.setStatus(HttpStatus.OK.value());
			apiResponse.setError(null);
			return apiResponse;
		}
		public ApiResponse getAllChildCategory() {
			 List<ChildCategory> childCategory=childCategoryRepo.findAll();
			 if(childCategory.isEmpty()) {
				 throw new IdNotFound("no ChildCategories Present");
			 }
			apiResponse.setData(childCategoryRepo.findAll());
			apiResponse.setStatus(HttpStatus.OK.value());
			apiResponse.setError(null);
			return apiResponse;
		}
		
		public ApiResponse getChildCategoryById(long id) {
			if(childCategoryRepo.findById(id).isEmpty()) {
				 throw new IdNotFound("Product Id not Found");
			}
			childCategoryRepo.findById(id).get();
			
				boolean status=childCategoryRepo.findById(id).get().isActive();
				if(status==true) {
					apiResponse.setData(childCategoryRepo.findById(id).get());
					apiResponse.setStatus(HttpStatus.OK.value());
					apiResponse.setError(null);
					}
				else {
					apiResponse.setData(null);
					apiResponse.setStatus(HttpStatus.OK.value());
					apiResponse.setError("childcategory not present");
				}
				return apiResponse;
				}
			
			
		
//		public ApiResponse getchildCategoryByName(String name) {
//		ChildCategory cc=childCategoryRepo.findByName(name);
//			apiResponse.setData(cc);
	//	apiResponse.setStatus(HttpStatus.OK.value());
		//apiResponse.setError(null);
//			return apiResponse;
//		}
		public ApiResponse updateChildCategory(ChildCategory childCategory,Long id) {
			 if(childCategoryRepo.findById(id).isEmpty()){
		            throw new IdNotFound("Product Id not Found");
		       }
			ChildCategory existingChild=childCategoryRepo.findById(id).get();
			existingChild.setName(childCategory.getName());
			existingChild.setActive(childCategory.isActive());
			apiResponse.setData(childCategoryRepo.save(existingChild));
			apiResponse.setStatus(HttpStatus.OK.value());
			apiResponse.setError(null);
			   return apiResponse;
		}
		public ApiResponse deleteChildCategory(long id) {
			if(childCategoryRepo.findById(id).isEmpty()) {
				 throw new IdNotFound("Product Id not Found");
			}
			childCategoryRepo.deleteById(id);
			String msg="Id "+ id +" Deleted Successfully";
			apiResponse.setData(msg);
			apiResponse.setStatus(HttpStatus.OK.value());
			apiResponse.setError(null);
			 return apiResponse;
		}
		
	}


