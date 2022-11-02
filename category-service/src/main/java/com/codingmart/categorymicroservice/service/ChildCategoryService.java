package com.codingmart.categorymicroservice.service;


import java.util.List;
import java.util.Objects;

import com.codingmart.categorymicroservice.repository.ChildCategoryRepository;

import org.springframework.stereotype.Service;

import com.codingmart.categorymicroservice.entity.ChildCategory;
import com.codingmart.categorymicroservice.response.ApiResponse;
import com.codingmart.categorymicroservice.response.IdNotFound;

@Service
public class ChildCategoryService {
	

		private final ChildCategoryRepository childCategoryRepo;
		private final ApiResponse apiResponse;



		public ChildCategoryService(ChildCategoryRepository childCategoryRepo,ApiResponse apiResponse) {
			this.childCategoryRepo=childCategoryRepo;
			this.apiResponse=apiResponse;
		}
		
		public ApiResponse saveChildCategory(ChildCategory childCategory) {
			apiResponse.resetResponse();
			if(Objects.isNull(childCategoryRepo.findByName(childCategory.getName()))) {
			    apiResponse.setData(childCategoryRepo.save(childCategory));
			}
			else {
				apiResponse.setError("ChildCategory already Exist");
			}
			return apiResponse;
		}
		
		public ApiResponse saveAllChildCategory(List<ChildCategory> childCategories){
			apiResponse.resetResponse();
			for(ChildCategory childCategory : childCategories)
			{
				ChildCategory childCategory1=childCategoryRepo.findByName(childCategory.getName());
				if(Objects.isNull(childCategory1))
					apiResponse.setData(childCategoryRepo.save(childCategory));
			}
			return apiResponse;
		}
		public ApiResponse getAllChildCategory() {
			apiResponse.resetResponse();
			List<ChildCategory> childCategory=childCategoryRepo.findAll();
			 if(childCategory.isEmpty()) {
				 throw new IdNotFound("No ChildCategories Present");
			 }
			apiResponse.setData(childCategoryRepo.findAll());
			return apiResponse;
		}
		
		public ApiResponse getChildCategoryById(long id) {
			apiResponse.resetResponse();
			if(childCategoryRepo.findById(id).isEmpty()) {
				 throw new IdNotFound("ChildCategory not Found");
			}
			boolean status=childCategoryRepo.findById(id).get().isActive();
			if(status) {
				apiResponse.setData(childCategoryRepo.findById(id).get());
			}
			else {
			     apiResponse.setError("ChildCategory not present");
			}
			return apiResponse;
		}

		public ApiResponse getChildCategoryByName(String name) {
			apiResponse.resetResponse();
			apiResponse.setData(childCategoryRepo.findByName(name));
			return apiResponse;
		}
		public ApiResponse updateChildCategory(ChildCategory childCategory,Long id) {
			apiResponse.resetResponse();
			 if(childCategoryRepo.findById(id).isEmpty()){
		            throw new IdNotFound("ChildCategory not Found");
		       }
			ChildCategory existingChild=childCategoryRepo.findById(id).get();
			existingChild.setName(childCategory.getName());
			existingChild.setActive(childCategory.isActive());
			apiResponse.setData(childCategoryRepo.save(existingChild));
			return apiResponse;
		}
		public ApiResponse deleteChildCategory(long id) {
			apiResponse.resetResponse();
			if(childCategoryRepo.findById(id).isEmpty()) {
				 throw new IdNotFound("ChildCategory not Found");
			}
			childCategoryRepo.deleteById(id);
			apiResponse.setData("Id "+ id +" Deleted Successfully");
			 return apiResponse;
		}
		
	}


