package com.example.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.entity.ChildCategory;
import com.example.response.ApiResponse;
import com.example.service.ChildCategoryService;

@RestController
public class ChildCategoryController {

		private ChildCategoryService childCategoryService;
		
		
		
		public ChildCategoryController(ChildCategoryService childCategoryService) {
			this.childCategoryService=childCategoryService;
			
		}
		
		@PostMapping("/savechildCategory")
		public ApiResponse saveSubCategory(@RequestBody ChildCategory childCategory) {
			return childCategoryService.saveChildCategory(childCategory);
		}
		@PostMapping("/addChildCategory")
		public ApiResponse addSubCategory(@RequestBody List<ChildCategory> subCategories){
			return childCategoryService.addChildCategory(subCategories);
		}
		@GetMapping("/childCategories")
		public ApiResponse getChildCategory(){
			return childCategoryService.getChildCategory();
		}
		@GetMapping("/childCategory/{id}")
		public ApiResponse getSubCategoryById(@PathVariable long id) {
			return childCategoryService.getChildCategoryById(id);
		}
//		@GetMapping("/ChildCategory/{name}")
//		public ApiResponse getChildCategoryByName(@PathVariable String name) {
//			return childCategoryService.getChildCategoryByName(name);
//		}
		@PutMapping("/updateChildCategory")
		public ApiResponse updateSubCategoy(@RequestBody ChildCategory subCategory) {
			return childCategoryService.updateChildCategory(subCategory);
		}
		@DeleteMapping("/deletechildCategory/{id}")
		public ApiResponse deleteSubCategory(@PathVariable long id) {
			return childCategoryService.deletechildCategory(id);
		}
	}
        