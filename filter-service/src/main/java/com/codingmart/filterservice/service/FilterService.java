package com.codingmart.filterservice.service;

import com.codingmart.productmicroservice.entity.Product;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class FilterService {

    public List<Product> getProductsByColor(List<Product> products, String color) {
        List<Product> filteredProducts = new ArrayList<>();
       for(Product product:products){
            if(product.getColor().equals(color)){
                filteredProducts.add(product);
            }
        }
       return filteredProducts;
    }


    public List<Product> getProductsByPrice(List<Product> products, Double price) {
        List<Product> filteredProducts = new ArrayList<>();
        for (Product product : products) {
            if (product.getPrice() < price)
                filteredProducts.add(product);
        }

        return filteredProducts;
    }



    public List<Product> getProductsByBrand(List<Product> products, String brandName){
        System.out.println(brandName);
        List<Product> filteredProducts=new ArrayList<>();
        for(Product product:products) {
            if (product.getBrand().getName().trim().equalsIgnoreCase(brandName.trim())) {
                filteredProducts.add(product);
            }
        }
        return filteredProducts;
    }






}
