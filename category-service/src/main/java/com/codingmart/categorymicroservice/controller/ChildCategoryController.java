package com.codingmart.categorymicroservice.controller;

import java.util.List;

import com.codingmart.productmicroservice.entity.Product;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.codingmart.categorymicroservice.entity.ChildCategory;
import com.codingmart.categorymicroservice.response.ApiResponse;
import com.codingmart.categorymicroservice.service.ChildCategoryService;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/ChildCategories")
public class ChildCategoryController {

		private final ChildCategoryService childCategoryService;
		private final RestTemplate restTemplate;
		
		public ChildCategoryController(ChildCategoryService childCategoryService, RestTemplate restTemplate) {
			this.childCategoryService=childCategoryService;
			this.restTemplate = restTemplate;
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

	@GetMapping("/products/id")
	public ApiResponse getAllProductsByChildCategoryId(@PathVariable("id") long id){
		ResponseEntity<Product[]> productResponse=restTemplate.getForEntity("http://192.168.1.76:9191/meesho-productmicroservice/products/child-category/active/"+id,Product[].class);
		Product[] products= productResponse.getBody();
		return childCategoryService.getAllProductsByChildCategoryId(products);
	}



	}
        