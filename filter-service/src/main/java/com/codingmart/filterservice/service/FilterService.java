package com.codingmart.filterservice.service;

import com.codingmart.productmicroservice.custom.GenericResponse;
import com.codingmart.productmicroservice.entity.Brand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
public class FilterService {

    private final GenericResponse genericResponse;

    @Autowired
    public FilterService(GenericResponse genericResponse) {
        this.genericResponse = genericResponse;
    }

    public GenericResponse getProductsByColor(List<HashMap> products, String color) {
        List<HashMap> filteredProducts=new ArrayList<>();
//        for(int i=0;i<products.size();i++){
//            if(products.get(i).getColor().equals(color)){
//                filteredProducts.add(products.get(i));
//            }
//        }
        for(HashMap product:products){
            if(product.get("color").equals(color)) {
                filteredProducts.add(product);
                System.out.println(product);
            }
        }
        System.out.println(filteredProducts);
        genericResponse.setData(filteredProducts);
        return genericResponse;
    }

    public GenericResponse getProductsByBrand(List<HashMap> products, String brandName) {
        List<HashMap> filteredProducts=new ArrayList<>();
        for(HashMap product:products) {
            if(product.get("name").equals(brandName))
                filteredProducts.add(product);
        }
        genericResponse.setData(filteredProducts);
        return genericResponse;
    }
}
