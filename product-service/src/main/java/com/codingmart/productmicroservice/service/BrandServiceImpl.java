package com.codingmart.productmicroservice.service;

import com.codingmart.productmicroservice.entity.Brand;
import com.codingmart.productmicroservice.exception.NotFoundException;
import com.codingmart.productmicroservice.repository.BrandRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class BrandServiceImpl implements BrandService{

    private final BrandRepository brandRepository;

    @Autowired
    public BrandServiceImpl(BrandRepository brandRepository){
        this.brandRepository=brandRepository;
    }
    @Override
    public Brand addBrand(Brand brand) {
        return brandRepository.save(brand);
    }

    @Override
    public List<Brand> getAllBrands() {
        List<Brand> brands=brandRepository.findAll();
        if(brands.isEmpty()){
            throw new NotFoundException("Brands are not found");
        }
        return brands;
    }

    @Override
    public Brand getBrand(Long id) {
        if(brandRepository.findById(id).isEmpty()){
            throw new NotFoundException("Brand Id not Found");
        }
        return brandRepository.findById(id).get();
    }

    @Override
    public String deleteBrand(Long id) {
        if(brandRepository.findById(id).isEmpty()){
            throw new NotFoundException("Brand Id not Found");
        }
        brandRepository.deleteById(id);
        return "Brand Deleted Successfully";
    }

    @Override
    public Brand updateBrand(Brand brand,Long id) {
        if(brandRepository.findById(id).isEmpty()){
            throw new NotFoundException("Brand Id not Found");
        }
        Brand existingBrand=brandRepository.findById(id).get();
        if(Objects.nonNull(brand.getName()) && !"".equals(brand.getName())){
            existingBrand.setName(brand.getName());
        }
        if(Objects.nonNull(brand.getActive())) {
            existingBrand.setActive(brand.getActive());
        }
        return brandRepository.save(existingBrand);
    }

    @Override
    public Brand getBrandByName(String name) {
        return brandRepository.findByName(name);
    }
}
