package com.example.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.entity.Category;
import com.example.response.ApiResponse;
import com.example.service.CategoryService;

@RestController
public class CategoryController {

	private CategoryService categoryService;
	
	public CategoryController(CategoryService categoryService) {
		this.categoryService=categoryService;
	}

	@PostMapping("/saveCategory")
	public ApiResponse saveCategory(@RequestBody Category category) {
		return categoryService.saveCategory(category);
	}

	@PostMapping("/saveAllCategory")
	public ApiResponse saveAllCategory(@RequestBody List<Category> categories){
		return categoryService.saveAllCategory(categories);
	}

	@GetMapping("/getAllCategories")
	public ApiResponse getAllCategory(){
		return categoryService.getAllCategories();
		}
	
	@GetMapping("/getCategoryById/{id}")
	public ApiResponse getCategoryById(@PathVariable long id) {
	 return categoryService.getCategoryById(id);
		}
	
//	@GetMapping("/category/{name}")
//	public ApiResponse getCategoryByName(@PathVariable String name) {
//		return categoryService.getCategoryByName(name);
//	}
	@PutMapping("/UpdateMapping")
	public ApiResponse updateCategory(@RequestBody Category category) {
		return categoryService.UpdateCategory(category);
	}
	@DeleteMapping("/delete/{id}")
	public ApiResponse deleteCategory(@PathVariable long id)
	{
		return categoryService.deleteCategory(id);
	}
	
	@GetMapping("/getActiveCategoryById/{id}")
	public ApiResponse getActiveCategoryById(@PathVariable long id){
		return categoryService.getActiveCategoryById(id);
	}
	
}
