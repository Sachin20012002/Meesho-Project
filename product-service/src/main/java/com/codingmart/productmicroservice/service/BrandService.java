package com.codingmart.productmicroservice.service;

import com.codingmart.productmicroservice.entity.Brand;

import java.util.List;

public interface BrandService {
    Brand addBrand(Brand brand);

    List<Brand> getAllBrands();

    Brand getBrand(Long id);

    String deleteBrand(Long id);

    Brand updateBrand(Brand brand,Long id);

    Brand getBrandByName(String name);
}
