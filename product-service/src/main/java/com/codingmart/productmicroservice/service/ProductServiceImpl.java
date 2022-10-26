package com.codingmart.productmicroservice.service;

import com.codingmart.productmicroservice.entity.*;
import com.codingmart.productmicroservice.exception.NotFoundException;
import com.codingmart.productmicroservice.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class ProductServiceImpl implements ProductService{

    private final ProductRepository productRepository;
    private final TaxRepository taxRepository;
    private final BrandRepository brandRepository;
    private final TypeRepository typeRepository;
    private final DiscountRepository discountRepository;


    @Autowired
    public ProductServiceImpl(ProductRepository productRepository, TaxRepository taxRepository, BrandRepository brandRepository, TypeRepository typeRepository, DiscountRepository discountRepository){
        this.productRepository=productRepository;
        this.taxRepository = taxRepository;
        this.brandRepository = brandRepository;
        this.typeRepository = typeRepository;
        this.discountRepository = discountRepository;
    }


    @Override
    public Product getProductById(Long id) {
        if(productRepository.findById(id).isEmpty()){
            throw new NotFoundException("Product Id not Found");
        }
        return productRepository.findById(id).get();
    }

    @Override
    public Product getProductByName(String name) {
        if(Objects.nonNull(productRepository.findByName(name))){
            throw new NotFoundException("Product Name not Found");
        }
        return productRepository.findByName(name);
    }


    @Override
    public Product addProduct(Product product) {
        product.setTaxes(updateAndSaveTaxes(product.getTaxes()));
        product.setType(updateAndSaveType(product.getType()));
        product.setBrand(updateAndSaveBrand(product.getBrand()));
        product.setDiscount(updateAndSaveDiscount(product.getDiscount()));

        product.setQuantity(calculateTotalQuantityFromSizes(product.getAvailableSizes()));
        product.setMaximumRetailPrice(calculateMaximumRetailPrice(product.getTaxes(),product.getPrice()));
        product.setFinalDiscountedPrice(calculateFinalDiscountedPrice(product.getDiscount(),product.getPrice()));

        Product updatedProduct=productRepository.save(product);
        updatedProduct.setCode(generateProductCode(product));
        return productRepository.save(updatedProduct);
    }


    @Override
    public String deleteProduct(Long id) {
        if(productRepository.findById(id).isEmpty()){
            throw new NotFoundException("Product Id not Found");
        }
        productRepository.deleteById(id);
        return "Product deleted Successfully";
    }


    @Override
    public Product updateProduct(Long id, Product product) {
        if(productRepository.findById(id).isEmpty()){
            throw new NotFoundException("Product Id not Found");
        }
        Product existingProduct=productRepository.findById(id).get();

        if(Objects.nonNull(product.getName()) && !"".equalsIgnoreCase(product.getName())){
            existingProduct.setName(product.getName());
        }

        if(Objects.nonNull(product.getPrice())){
            existingProduct.setPrice(product.getPrice());
        }

        if(Objects.nonNull(product.getColor()) && !"".equalsIgnoreCase(product.getColor())){
            existingProduct.setName(product.getColor());
        }

        if(Objects.nonNull(product.getDescription()) && !"".equalsIgnoreCase(product.getDescription())){
            existingProduct.setName(product.getDescription());
        }

        if(Objects.nonNull(product.getChildCategoryId())){
            existingProduct.setChildCategoryId(product.getChildCategoryId());
        }

        if(Objects.nonNull(product.getBrand())){
           existingProduct.setBrand(updateAndSaveBrand(product.getBrand()));
        }

        if(Objects.nonNull(product.getImages())){
            existingProduct.setImages(product.getImages());
        }

        if(Objects.nonNull(product.getAvailableSizes())){
            existingProduct.setQuantity(calculateTotalQuantityFromSizes(product.getAvailableSizes()));
            existingProduct.setAvailableSizes(product.getAvailableSizes());
        }
        if(Objects.nonNull(product.getTaxes())){
            existingProduct.setMaximumRetailPrice(calculateMaximumRetailPrice(product.getTaxes(),product.getPrice()));
            existingProduct.setTaxes(updateAndSaveTaxes(product.getTaxes()));
        }

        if(Objects.nonNull(product.getDiscount())){
            existingProduct.setFinalDiscountedPrice(calculateFinalDiscountedPrice(product.getDiscount(),product.getPrice()));
            existingProduct.setDiscount(updateAndSaveDiscount(product.getDiscount()));
        }

        productRepository.save(existingProduct);
        return existingProduct;
    }

    @Override
    public List<Product> getAllProductsByChildCategoryId(Long id) {
        if(Objects.nonNull(productRepository.findAllByChildCategoryId(id))){
            throw new NotFoundException("Products not Found");
        }
        return productRepository.findAllByChildCategoryId(id);
    }

    @Override
    public List<Product> getAllProductsByTypeId(Long id) {
        if(Objects.isNull(productRepository.findAllByTypeId(id)))
            throw new NotFoundException("Products not found");
        return productRepository.findAllByTypeId(id);
    }

    @Override
    public List<Product> getAllProductsByBrandId(Long id) {
        if(Objects.isNull(productRepository.findAllByBrandId(id)))
            throw new NotFoundException("Products not found");
        return productRepository.findAllByBrandId(id);
    }

    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public List<Product> getAllProductsByColor(String color) {
        return productRepository.findAllByColor(color);
    }

    @Override
    public List<Product> getAllActiveProducts() {
        return productRepository.findAllByActive(true);
    }

    @Override
    public List<Product> getAllActiveProductsByChildCategoryId(Long id) {
        return productRepository.findAllByActiveAndChildCategoryId(true,id);
    }

    private Long calculateTotalQuantityFromSizes(List<Size> availableSizes) {
        Long quantity=0L;
        for(Size i:availableSizes){
            if(i.getActive()) quantity+=i.getQuantity();
        }
        return quantity;
    }

    private Double calculateMaximumRetailPrice(List<Tax> taxes, Double price) {
        Double maximumRetailPrice=price;
        for(Tax i:taxes) {
            if(i.getActive()) maximumRetailPrice += ((i.getPercent() / 100.0) * price);
        }
        return maximumRetailPrice;
    }

    private Double calculateFinalDiscountedPrice(Discount discount, Double price) {
        if(Objects.isNull(discount) || !discount.getActive()){
            return price;
        }
        return price-(price*discount.getPercent()/100.0);
    }

    private List<Tax> updateAndSaveTaxes(List<Tax> taxes) {
        List<Tax> productTaxes=new ArrayList<>();
        for(Tax i:taxes){
            if(Objects.isNull(taxRepository.findByName(i.getName())))
                taxRepository.save(i);
            productTaxes.add(taxRepository.findByName(i.getName()));
        }
        return productTaxes;
    }

    private Type updateAndSaveType(Type type) {
        if(Objects.isNull(typeRepository.findByName(type.getName())))
            typeRepository.save(type);
        return typeRepository.findByName(type.getName());
    }

    private Brand updateAndSaveBrand(Brand brand) {

        if(Objects.nonNull(brand)) {
            if (Objects.isNull(brandRepository.findByName(brand.getName())))
                brandRepository.save(brand);
            return brandRepository.findByName(brand.getName());
        }
        return null;
    }

    private Discount updateAndSaveDiscount(Discount discount) {
        if (Objects.nonNull(discount)) {
            if (Objects.isNull(discountRepository.findByName(discount.getName())))
                discountRepository.save(discount);
            return discountRepository.findByName(discount.getName());
        }
        return null;
    }




    private String generateProductCode(Product product) {
        return product.getSupplierId()+"-"+product.getChildCategoryId()+"-"+product.getBrand().getId()+"-"+product.getId();
    }

}
