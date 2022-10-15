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
		public ApiResponse saveChildCategory(@RequestBody ChildCategory childCategory) {
			return childCategoryService.saveChildCategory(childCategory);
		}
		@PostMapping("/saveAllChildCategory")
		public ApiResponse saveAllChildCategory(@RequestBody List<ChildCategory> childCategories){
			return childCategoryService.saveAllChildCategory(childCategories);
		}
		@GetMapping("/childCategories")
		public ApiResponse getAllChildCategory(){
			return childCategoryService.getAllChildCategory();
		}
		@GetMapping("/childCategory/{id}")
		public ApiResponse getChildCategoryById(@PathVariable long id) {
			return childCategoryService.getChildCategoryById(id);
		}
//		@GetMapping("/ChildCategory/{name}")
//		public ApiResponse getChildCategoryByName(@PathVariable String name) {
//			return childCategoryService.getChildCategoryByName(name);
//		}
		@PutMapping("/updateChildCategory")
		public ApiResponse updateChildCategoy(@RequestBody ChildCategory childCategory) {
			return childCategoryService.updateChildCategory(childCategory);
		}
		@DeleteMapping("/deletechildCategory/{id}")
		public ApiResponse deleteChildCategory(@PathVariable long id) {
			return childCategoryService.deletechildCategory(id);
		}
	}
        