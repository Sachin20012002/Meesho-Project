package com.codingmart.productmicroservice.controller;

import com.codingmart.productmicroservice.custom.GenericResponse;
import com.codingmart.productmicroservice.entity.Product;
import com.codingmart.productmicroservice.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/products")
public class ProductController {
    private final ProductService productService;
    private final GenericResponse genericResponse;
    @Autowired
    public ProductController(ProductService productService, GenericResponse genericResponse){
        this.productService=productService;
        this.genericResponse = genericResponse;
    }

    @GetMapping("/{id}")
    public GenericResponse getProductById(@PathVariable("id") Long id){
        genericResponse.setData(productService.getProductById(id));
        return genericResponse;
    }

    @PostMapping(value = "",consumes = "application/json")
    public GenericResponse addProduct(@Valid @RequestBody Product product){
        genericResponse.setData(productService.addProduct(product));
        return genericResponse;
    }

    @DeleteMapping("/{id}")
    public GenericResponse deleteProduct(@PathVariable("id") Long id){
        genericResponse.setData(productService.deleteProduct(id));
        return genericResponse;
    }

    @PutMapping(value = "/{id}",consumes = "application/json")
    public GenericResponse updateProduct(@PathVariable("id") Long id,@Valid @RequestBody Product product){
        genericResponse.setData(productService.updateProduct(id,product));
        return genericResponse;
    }

     @GetMapping("/child-category/{id}")
     public GenericResponse getAllProductsByChildCategoryId(@PathVariable("id") Long id){
         genericResponse.setData(productService.getAllProductsByChildCategoryId(id));
         return genericResponse;
     }
    @GetMapping("/child-category/active/{id}")
    public GenericResponse getAllActiveProductsByChildCategoryId(@PathVariable("id") Long id){
        genericResponse.setData(productService.getAllActiveProductsByChildCategoryId(id));
        return genericResponse;
    }
    @GetMapping("")
    public GenericResponse getAllProducts(){
        genericResponse.setData(productService.getAllProducts());
        return genericResponse;
    }
    @GetMapping("/active")
    public GenericResponse getAllActiveProducts(){
        genericResponse.setData(productService.getAllActiveProducts());
        return genericResponse;
    }

/*
     @GetMapping("/type/{id}")
     public GenericResponse getAllProductsByTypeId(@PathVariable("id") Long id){
         genericResponse.setData(productService.getAllProductsByTypeId(id));
         return genericResponse;
     }

     @GetMapping("/brand/{id}")
    public GenericResponse getAllProductsByBrandId(@PathVariable("id") Long id){
         genericResponse.setData(productService.getAllProductsByBrandId(id));
         return genericResponse;
     }

     @GetMapping("/color/{color}")
    public GenericResponse getAllProductsByColor(@PathVariable String color){
        genericResponse.setData(productService.getAllProductsByColor(color));
        return genericResponse;
     }
*/



}
