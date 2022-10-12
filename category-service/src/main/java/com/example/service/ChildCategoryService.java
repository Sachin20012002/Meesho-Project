package com.example.service;

import java.util.List;
import java.util.Objects;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import com.example.entity.ChildCategory;
import com.example.entity.SubCategory;
import com.example.repository.ChildCategoryRepository;
import com.example.response.ApiResponse;

@Service
public class ChildCategoryService {
	

		private ChildCategoryRepository childCategoryRepo;
		private ApiResponse apiResponse;
		
		public ChildCategoryService(ChildCategoryRepository childCategoryRepo,ApiResponse apiResponse) {
			this.childCategoryRepo=childCategoryRepo;
			this.apiResponse=apiResponse;
		}
		
		public ApiResponse saveChildCategory(ChildCategory childCategory) {
			SubCategory subCategory1=childCategoryRepo.findByName(childCategory.getName());
			if(Objects.isNull(subCategory1)) {
			childCategoryRepo.save(childCategory);
			apiResponse.setData(childCategory);
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
		
		public ApiResponse addChildCategory(List<ChildCategory> childCategory){
			childCategoryRepo.saveAll(childCategory);
			apiResponse.setData(childCategory);
			apiResponse.setStatus(HttpStatus.OK.value());
			apiResponse.setError(null);
			return apiResponse;
		}
		public ApiResponse getChildCategory() {
			apiResponse.setData(childCategoryRepo.findAll());
			apiResponse.setStatus(HttpStatus.OK.value());
			apiResponse.setError(null);
			return apiResponse;
		}
		
		public ApiResponse getChildCategoryById(long id) {
			ChildCategory cc=childCategoryRepo.findById(id).get();
			
				boolean status=cc.isActive();
				if(status==true) {
					apiResponse.setData(cc);
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
		public ApiResponse updateChildCategory(ChildCategory childCategory) {
			ChildCategory existingChild=childCategoryRepo.findById(childCategory.getId()).get();
			existingChild.setName(childCategory.getName());
			existingChild.setActive(childCategory.isActive());
			apiResponse.setData(childCategoryRepo.save(existingChild));
			apiResponse.setStatus(HttpStatus.OK.value());
			apiResponse.setError(null);
			   return apiResponse;
		}
		public ApiResponse deletechildCategory(long id) {
			childCategoryRepo.deleteById(id);
			String msg="Id "+ id +" Deleted Successfully";
			apiResponse.setData(msg);
			apiResponse.setStatus(HttpStatus.OK.value());
			apiResponse.setError(null);
			 return apiResponse;
		}
		
	}


