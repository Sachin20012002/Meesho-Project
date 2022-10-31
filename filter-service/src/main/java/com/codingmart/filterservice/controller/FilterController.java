package com.codingmart.filterservice.controller;

import com.codingmart.filterservice.model.ProductList;
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
   public Object getRequestFromProduct(){
       Object products= restTemplate.getForObject("http://192.168.1.76:9191/meesho-productmicroservice/products",Object.class);
       System.out.println(products);
       // return "hi";
        return products;

//        ProductList productList=restTemplate.getForObject("http://192.168.1.76:9191/meesho-productmicroservice/products",ProductList.class);
//        System.out.println(productList);
//        List<Product> products=productList.getProducts();
//        System.out.println(products);
//        return products;

    }

}
