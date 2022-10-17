package com.codingmart.categorymicroservice.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.codingmart.categorymicroservice.entity.SubCategory;
import com.codingmart.categorymicroservice.response.ApiResponse;
import com.codingmart.categorymicroservice.service.SubCategoryService;

@RestController
@RequestMapping("/SubCategory")
public class SubCategoryController {
	
	private SubCategoryService subCategoryService;
	
	public SubCategoryController(SubCategoryService subCategoryService) {
		this.subCategoryService=subCategoryService;
	}
	
	@PostMapping()
	public ApiResponse saveSubCategory(@RequestBody SubCategory subCategory) {
		return subCategoryService.saveSubCategory(subCategory);
	}
	@PostMapping("/saveAllSubCategory")
	public ApiResponse saveAllSubCategory(@RequestBody List<SubCategory> subCategories){
		return subCategoryService.saveAllSubCategory(subCategories);
	}
	@GetMapping()
	public ApiResponse getAllSubCategory(){
		return subCategoryService.getAllSubCategory();
	}
	@GetMapping("/{id}")
	public ApiResponse getSubCategoryById(@PathVariable long id) {
		return subCategoryService.gesubCategoryById(id);
	}
//	@GetMapping("/subCategory/{name}")
//	public ApiResponse getSubCategoryByName(@PathVariable String name) {
//		return subCategoryService.getsubCategoryByName(name);
//	}
	@PutMapping()
	public ApiResponse updateSubCategoy(@RequestBody SubCategory subCategory) {
		return subCategoryService.updateSubCategory(subCategory);
	}
	@DeleteMapping("/{id}")
	public ApiResponse deleteSubCategory(@PathVariable long id) {
		return subCategoryService.deleteSubCategory(id);
	}
	
	@GetMapping("/getAllSubCategoriesFromCategoryId/{id}")
	public ApiResponse getAllSubCategoriesFromCategoryId(@PathVariable long id){
		return subCategoryService.getAllSubCategoriesFromCategoryId(id);
	}
}