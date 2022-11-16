package com.codingmart.filterservice.controller;


import com.codingmart.filterservice.service.FilterService;
import com.codingmart.productmicroservice.custom.GenericResponse;
import com.codingmart.productmicroservice.entity.Product;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;
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
    @CircuitBreaker(name = "FILTER_SERVICE",fallbackMethod = "filterServiceFallBack")
    @Retry(name = "FILTER_SERVICE")
    @RateLimiter(name = "FILTER_SERVICE")
   public GenericResponse getProductsByColor(@PathVariable("color") String color){
       return filterService.getProductsByColor(getProductsThroughRestTemplate(),color);
    }

//    @GetMapping("/brand1/{brandName}")
//    public GenericResponse getProductsByBrand1(@PathVariable("brandName") String brandName){
//        GenericResponse genericRequest= restTemplate.getForObject("http://sachin-latitude-3520:9191/meesho/product-microservice/products",GenericResponse.class);
//        List<Product> products= (List<Product>) genericRequest.getData();
//        System.out.println(products.get(0).getBrand().getName());
//       // return filterService.getProductsByBrand(products,brandName);
//        return new GenericResponse();
//    }

    @GetMapping("/price/{price}")
    @CircuitBreaker(name = "FILTER_SERVICE",fallbackMethod = "filterServiceFallBack")
    @Retry(name = "FILTER_SERVICE")
    @RateLimiter(name = "FILTER_SERVICE")
    public GenericResponse getProductsByPrice(@PathVariable("price") Double price){
        return filterService.getProductsByPrice(getProductsThroughRestTemplate(),price);
    }

    @GetMapping("/brand/{brandName}")
    @CircuitBreaker(name = "FILTER_SERVICE",fallbackMethod = "filterServiceFallBack")
    @Retry(name = "FILTER_SERVICE")
    @RateLimiter(name = "FILTER_SERVICE")
    public GenericResponse getProductsByBrand(@PathVariable("brandName") String brandName){
        return filterService.getProductsByBrand(getProductsThroughRestTemplate(),brandName);
    }


    public Product[] getProductsThroughRestTemplate(){
        ResponseEntity<Product[]> productResponse= restTemplate.getForEntity("http://localhost:9191/meesho/product-microservice/products/filter",Product[].class);
        return productResponse.getBody();
    }

    public GenericResponse filterServiceFallBack(Exception e){
        GenericResponse genericResponse=new GenericResponse();
        genericResponse.setData("Please try again later");
        return genericResponse;
    }

}
