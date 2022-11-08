package com.codingmart.categorymicroservice.service;


import com.codingmart.categorymicroservice.entity.ChildCategory;
import com.codingmart.categorymicroservice.repository.ChildCategoryRepository;
import com.codingmart.categorymicroservice.response.ApiResponse;
import com.codingmart.categorymicroservice.response.ErrorResponse;
import com.codingmart.categorymicroservice.response.IdNotFound;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

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
				apiResponse.setError(new ErrorResponse("ChildCategory already Exist","Avoid Duplicate Entry"));
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
			apiResponse.setData(childCategoryRepo.findById(id).get());
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


	public ApiResponse getAllChildCategoriesFromSubCategoryId(long id){
		apiResponse.resetResponse();
		if(childCategoryRepo.getAllChildCategoriesFromSubCategoryId(id).isEmpty()) {
			throw new IdNotFound("No SubCategory found for the given Category Id");
		}
		List<ChildCategory> subCategoryList=childCategoryRepo.getAllChildCategoriesFromSubCategoryId(id);
		apiResponse.setData(subCategoryList);

		return apiResponse;
	}

	public ApiResponse getAllActiveChildCategory() {
		apiResponse.resetResponse();
		List<ChildCategory> childCategories=childCategoryRepo.findAll();
		List<ChildCategory> activeChildCategories=childCategories.stream().filter((i)->i.isActive()).collect(Collectors.toList());
		if(childCategories.isEmpty()) {
			throw new IdNotFound("No ChildCategories Present");
		}
		apiResponse.setData(activeChildCategories);
		return apiResponse;

	}
}


