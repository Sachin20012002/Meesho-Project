package com.codingmart.filterservice.service;

import com.codingmart.productmicroservice.custom.GenericResponse;
import com.codingmart.productmicroservice.entity.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class FilterService {

    private final GenericResponse genericResponse;

    @Autowired
    public FilterService(GenericResponse genericResponse) {
        this.genericResponse = genericResponse;
    }

    public GenericResponse getProductsByColor(Product[] products, String color) {
        System.out.println(color);
        List<Product> filteredProducts = new ArrayList<>();
       for(Product product:products){
            if(product.getColor().equals(color)){
                filteredProducts.add(product);
            }
        }
        genericResponse.setData(filteredProducts);
        return genericResponse;
    }

//    public GenericResponse getProductsByBrand(List<HashMap> products, String brandName) {
//        List<HashMap> filteredProducts = new ArrayList<>();
//        for (HashMap product : products) {
//            if (product.get("name").equals(brandName))
//                filteredProducts.add(product);
//        }
//        genericResponse.setData(filteredProducts);
//        return genericResponse;
//    }


    public GenericResponse getProductsByPrice(Product[] products, Double price) {
        List<Product> filteredProducts = new ArrayList<>();
        for (Product product : products) {
            if (product.getPrice() < price)
                filteredProducts.add(product);
        }
        genericResponse.setData(filteredProducts);
        return genericResponse;
    }



    public GenericResponse getProductsByBrand(Product[] products, String brandName){
        System.out.println(brandName);
        List<Product> filteredProducts=new ArrayList<>();
        for(Product product:products) {
            if (product.getBrand().getName().trim().equalsIgnoreCase(brandName.trim())) {
                filteredProducts.add(product);
            }
        }
        genericResponse.setData(filteredProducts);
        return genericResponse;
    }






}
