package com.codingmart.filterservice.controller;

import com.codingmart.productmicroservice.custom.GenericResponse;
import com.codingmart.productmicroservice.entity.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;


@RestController
@RequestMapping("/filters")
public class FilterController {

    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("/hi")
   public GenericResponse getRequestFromProduct(){
       GenericResponse genericResponse= restTemplate.getForObject("http://192.168.1.139:9191/meesho-productmicroservice/products",GenericResponse.class);
        List<Product> products= (List<Product>) genericResponse.getData();
        System.out.println(products.get(0));
       return genericResponse;

    }

}
