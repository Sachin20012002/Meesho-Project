package com.codingmart.categorymicroservice.controller;

import com.codingmart.categorymicroservice.entity.ChildCategory;
import com.codingmart.categorymicroservice.response.ApiResponse;
import com.codingmart.categorymicroservice.service.ChildCategoryService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ChildCategories")
public class ChildCategoryController {

		private final ChildCategoryService childCategoryService;
		private final ApiResponse apiResponse;

		
		public ChildCategoryController(ChildCategoryService childCategoryService, ApiResponse apiResponse) {
			this.childCategoryService=childCategoryService;
			this.apiResponse = apiResponse;
		}
		
		@PostMapping()
		public ApiResponse saveChildCategory(@RequestBody ChildCategory childCategory) {
			return childCategoryService.saveChildCategory(childCategory);
		}
		@PostMapping("/saveAllChildCategory")
		public ApiResponse saveAllChildCategory(@RequestBody List<ChildCategory> childCategories){
			return childCategoryService.saveAllChildCategory(childCategories);
		}
		@GetMapping()
		public ApiResponse getAllChildCategory(){
			return childCategoryService.getAllChildCategory();
		}
		@GetMapping("/get/{id}")
		public ApiResponse getChildCategoryById(@PathVariable long id) {
			return childCategoryService.getChildCategoryById(id);
		}
		@GetMapping("/ChildCategory/{name}")
		public ApiResponse getChildCategoryByName(@PathVariable String name) {
			return childCategoryService.getChildCategoryByName(name);
		}
		@PutMapping("/{id}")
		public ApiResponse updateChildCategory(@RequestBody ChildCategory childCategory,@PathVariable Long id) {
			return childCategoryService.updateChildCategory(childCategory,id);
		}
		@DeleteMapping("/{id}")
		public ApiResponse deleteChildCategory(@PathVariable long id) {
			return childCategoryService.deleteChildCategory(id);
		}

		@GetMapping("/getAllChildCategoriesFromSubCategoryId/{id}")
		public ApiResponse getAllChildCategoriesFromSubCategoryId(@PathVariable long id){
		return childCategoryService.getAllChildCategoriesFromSubCategoryId(id);
		}

	@GetMapping("/active")
	public ApiResponse getAllActiveChildCategory(){
		return childCategoryService.getAllActiveChildCategory();
	}

	@GetMapping("/products/{id}")
	public ApiResponse getAllProductsByChildCategoryId(@PathVariable("id") long id){

		apiResponse.setData(childCategoryService.getAllProductsByChildCategoryId(id));
		return apiResponse;
	}



	}
        