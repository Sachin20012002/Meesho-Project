package com.codingmart.filterservice.controller;


import com.codingmart.filterservice.service.FilterService;
import com.codingmart.productmicroservice.custom.GenericResponse;
import com.codingmart.productmicroservice.entity.Product;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Objects;


@RestController
@RequestMapping("/filters")
public class FilterController {

    private final RestTemplate restTemplate;
    private final GenericResponse<List<Product>> genericResponse;

    private final FilterService filterService;

    @Autowired
    public FilterController(RestTemplate restTemplate, GenericResponse<List<Product>> genericResponse, FilterService filterService) {
        this.restTemplate = restTemplate;
        this.genericResponse = genericResponse;
        this.filterService = filterService;
    }


    @GetMapping("/color/{color}")
    @CircuitBreaker(name = "FILTER_SERVICE",fallbackMethod = "filterServiceFallBack")
    @Retry(name = "FILTER_SERVICE")
    @RateLimiter(name = "FILTER_SERVICE")
   public GenericResponse<List<Product>> getProductsByColor(@PathVariable("color") String color){
       genericResponse.setData(filterService.getProductsByColor(getProductsThroughRestTemplate(),color));
       return genericResponse;
    }
    @GetMapping("/price/{price}")
    @CircuitBreaker(name = "FILTER_SERVICE",fallbackMethod = "filterServiceFallBack")
    @Retry(name = "FILTER_SERVICE")
    @RateLimiter(name = "FILTER_SERVICE")
    public GenericResponse<List<Product>> getProductsByPrice(@PathVariable("price") Double price){
        genericResponse.setData(filterService.getProductsByPrice(getProductsThroughRestTemplate(),price));
        return genericResponse;
    }

    @GetMapping("/brand/{brandName}")
    @CircuitBreaker(name = "FILTER_SERVICE",fallbackMethod = "filterServiceFallBack")
    @Retry(name = "FILTER_SERVICE")
    @RateLimiter(name = "FILTER_SERVICE")
    public GenericResponse<List<Product>> getProductsByBrand(@PathVariable("brandName") String brandName){
        genericResponse.setData(filterService.getProductsByBrand(getProductsThroughRestTemplate(),brandName));
        return genericResponse;
    }


    public List<Product> getProductsThroughRestTemplate(){
        return Objects.requireNonNull(restTemplate.exchange("http://sachin-latitude-3520:9191/meesho/product-microservice/products",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<GenericResponse<List<Product>>>() {
                }).getBody()).getData();
    }

    public GenericResponse<String> filterServiceFallBack(Exception ignoredE){
        GenericResponse<String> genericResponse=new GenericResponse<>();
        genericResponse.setData("Please try again later");
        return genericResponse;
    }

}
