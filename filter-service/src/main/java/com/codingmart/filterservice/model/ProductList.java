package com.codingmart.filterservice.model;

import com.codingmart.productmicroservice.entity.Product;

import java.util.ArrayList;
import java.util.List;

public class ProductList {
    private List<Product> products;
    public ProductList(){
        products=new ArrayList<>();
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    @Override
    public String toString() {
        return "ProductList{" +
                "products=" + products +
                '}';
    }
}
