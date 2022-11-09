package com.codingmart.categorymicroservice.controller;

import com.codingmart.categorymicroservice.entity.Category;
import com.codingmart.categorymicroservice.entity.SubCategory;
import com.codingmart.categorymicroservice.response.ApiResponse;
import com.codingmart.categorymicroservice.service.CategoryService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/categories")
public class CategoryController {

	private final CategoryService categoryService;
	private final ApiResponse apiResponse;
	
	public CategoryController(CategoryService categoryService, ApiResponse apiResponse) {
		this.categoryService=categoryService;
		this.apiResponse = apiResponse;
	}

	@PostMapping()
	public ApiResponse saveCategory(@RequestBody Category category) {
		return categoryService.saveCategory(category);
	}

	@PostMapping("/saveAllCategory")
	public ApiResponse saveAllCategory(@RequestBody List<Category> categories){
		return categoryService.saveAllCategory(categories);
	}

	@GetMapping("")
	public ApiResponse getAllCategory(){

		return categoryService.getAllCategories();
		}
	
	@GetMapping("/{id}")
	public ApiResponse getCategoryById(@PathVariable long id) {
	 return categoryService.getCategoryById(id);
		}
	
	@GetMapping("/category/{name}")
	public ApiResponse getCategoryByName(@PathVariable String name) {
		return categoryService.getCategoryByName(name);
	}
	
	
	@PutMapping("/{id}")
	public ApiResponse updateCategory(@RequestBody Category category,@PathVariable("id")Long id) {

		return categoryService.UpdateCategory(category,id);
	}
	@DeleteMapping("/{id}")
	public ApiResponse deleteCategory(@PathVariable long id)
	{

		return categoryService.deleteCategory(id);
	}
	
//	@GetMapping("/getActiveCategoryById/{id}")
//	public ApiResponse getActiveCategoryById(@PathVariable long id){
//		return categoryService.getActiveCategoryById(id);
//	}
	@PostMapping("/{id}")
	public ApiResponse saveSubCategoryForCategory(@RequestBody SubCategory subCategory,@PathVariable long id){
		return categoryService.saveSubCategoryForCategory(subCategory,id);
	}

	@GetMapping("/active")
	public ApiResponse getAllActiveCategory(){

		return categoryService.getAllActiveCategories();
	}
	@GetMapping("/products/{id}")
	public ApiResponse getAllProductsFromCategoryId(@PathVariable("id") long id){
		apiResponse.resetResponse();
		apiResponse.setData(categoryService.getAllProductsFromCategoryId(id));
		return apiResponse;
	}

}
