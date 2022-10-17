package com.example.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.entity.ChildCategory;
import com.example.response.ApiResponse;
import com.example.service.ChildCategoryService;

@RestController
@RequestMapping("/ChildCategory")
public class ChildCategoryController {

		private ChildCategoryService childCategoryService;
		
		public ChildCategoryController(ChildCategoryService childCategoryService) {
			this.childCategoryService=childCategoryService;
			
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
		@GetMapping("/{id}")
		public ApiResponse getChildCategoryById(@PathVariable long id) {
			return childCategoryService.getChildCategoryById(id);
		}
//		@GetMapping("/ChildCategory/{name}")
//		public ApiResponse getChildCategoryByName(@PathVariable String name) {
//			return childCategoryService.getChildCategoryByName(name);
//		}
		@PutMapping()
		public ApiResponse updateChildCategoy(@RequestBody ChildCategory childCategory) {
			return childCategoryService.updateChildCategory(childCategory);
		}
		@DeleteMapping("/{id}")
		public ApiResponse deleteChildCategory(@PathVariable long id) {
			return childCategoryService.deletechildCategory(id);
		}
	}
        