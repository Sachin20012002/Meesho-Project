package com.codingmart.filterservice.controller;


import com.codingmart.filterservice.service.FilterService;
import com.codingmart.productmicroservice.custom.GenericResponse;
import com.codingmart.productmicroservice.entity.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;


@RestController
@RequestMapping("/filters")
public class FilterController {

    private final RestTemplate restTemplate;

    private final FilterService filterService;

    @Autowired
    public FilterController(RestTemplate restTemplate, FilterService filterService) {
        this.restTemplate = restTemplate;
        this.filterService = filterService;
    }


    @GetMapping("/color/{color}")
   public GenericResponse getProductsByColor(@PathVariable("color") String color){
        return filterService.getProductsByColor(getProductsThroughRestTemplate(),color);
    }

//    @GetMapping("/brand/{brandName}")
//    public GenericResponse getProductsByBrand(@PathVariable("brandName") String brandName){
//        GenericRequest genericRequest= restTemplate.getForObject("http://192.168.1.76:9191/meesho-productmicroservice/products",GenericRequest.class);
//        List<HashMap> products= (List<HashMap>) genericRequest.getData();
//        return filterService.getProductsByBrand(products,brandName);
//    }

    @GetMapping("/price/{price}")
    public GenericResponse getProductsByPrice(@PathVariable("price") Double price){
        return filterService.getProductsByPrice(getProductsThroughRestTemplate(),price);
    }

    @GetMapping("/brand/{brandName}")
    public GenericResponse getProductsByBrand(@PathVariable("brandName") String brandName){
        return filterService.getProductsByBrand(getProductsThroughRestTemplate(),brandName);
    }

    public Product[] getProductsThroughRestTemplate(){
        ResponseEntity<Product[]> productResponse= restTemplate.getForEntity("http://192.168.1.76:9191/meesho/product-microservice/products/filter",Product[].class);
        return productResponse.getBody();
    }

}
