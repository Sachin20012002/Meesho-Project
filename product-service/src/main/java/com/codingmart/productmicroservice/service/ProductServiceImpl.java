package com.codingmart.productmicroservice.service;

import com.codingmart.productmicroservice.entity.*;
import com.codingmart.productmicroservice.enums.Response;
import com.codingmart.productmicroservice.exception.NotFoundException;
import com.codingmart.productmicroservice.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
public class ProductServiceImpl implements ProductService{

    private final ProductRepository productRepository;
    private final TaxRepository taxRepository;
    private final BrandRepository brandRepository;
    private final TypeRepository typeRepository;
    private final DiscountRepository discountRepository;
    private final ProductDetailRepository productDetailRepository;
    private final ImageService imageService;
    private final SizeService sizeService;


    @Autowired
    public ProductServiceImpl(ProductRepository productRepository, TaxRepository taxRepository, BrandRepository brandRepository, TypeRepository typeRepository, DiscountRepository discountRepository, ProductDetailRepository productDetailRepository, ImageService imageService, SizeService sizeService){
        this.productRepository=productRepository;
        this.taxRepository = taxRepository;
        this.brandRepository = brandRepository;
        this.typeRepository = typeRepository;
        this.discountRepository = discountRepository;
        this.productDetailRepository = productDetailRepository;
        this.imageService = imageService;
        this.sizeService = sizeService;
    }


    @Override
    public Product getProductById(Long id) {
        if(productRepository.findById(id).isEmpty()){
            throw new NotFoundException("Product Id not Found");
        }
        return productRepository.findById(id).get();
    }

    @Override
    @Transactional
    public Product addProduct(Product product) {
        updateAndSaveTransientObjects(product,product);
        Product updatedProduct=productRepository.save(product);
        updatedProduct.setCode(generateProductCode(product));
        return productRepository.save(updatedProduct);
    }




    @Override
    public Response deleteProduct(Long id) {
        if(productRepository.findById(id).isEmpty()){
            throw new NotFoundException("Product Id not Found");
        }
        productRepository.deleteById(id);
        //enum
        return Response.DELETED;
    }


    @Override
    public Product updateProduct(Long id, Product product) {
        if(productRepository.findById(id).isEmpty()){
            throw new NotFoundException("Product Id not Found");
        }
        Product existingProduct=productRepository.findById(id).get();
        existingProduct.setName(product.getName());
        existingProduct.setPrice(product.getPrice());
        existingProduct.setSupplierId(product.getSupplierId());
        existingProduct.setDescription(product.getDescription());
        existingProduct.setChildCategoryId(product.getChildCategoryId());
        updateAndSaveTransientObjects(product,existingProduct);
        existingProduct.setProductDetails(updatedProductDetails(product.getProductDetails(),existingProduct.getProductDetails()));
        existingProduct.setAvailableSizes(updatedAvailableSizes(product.getAvailableSizes(),existingProduct.getAvailableSizes()));
        existingProduct.setImages(updatedImages(product.getImages(),existingProduct.getImages()));
        return productRepository.save(existingProduct);

    }

    private List<Image> updatedImages(List<Image> images, List<Image> existingImages) {

        List<Image> updatedImages=new ArrayList<>();
        HashMap<String,Image> existingImageNames=new HashMap<>();
        HashSet<String> newImageNames=new HashSet<>();
        for(Image image:images)
            newImageNames.add(image.getName());
        for(Image image:existingImages){
            if(!newImageNames.contains(image.getName())){
                imageService.deleteImage(image.getId());
            }
            else{
                existingImageNames.put(image.getName(),image);
            }
        }
        for(Image image:images){
            if(!existingImageNames.containsKey(image.getName())){
                updatedImages.add(imageService.addImage(image));
            }
            else{
                updatedImages.add(imageService.updateImage(image,existingImageNames.get(image.getName()).getId()));
            }
        }
        return updatedImages;
    }

    private List<Size> updatedAvailableSizes(List<Size> availableSizes, List<Size> existingAvailableSizes) {
        List<Size> updatedAvailableSizes=new ArrayList<>();
        HashMap<String,Size> existingSizesNames=new HashMap<>();
        HashSet<String> newSizesNames=new HashSet<>();
        for(Size size:availableSizes)
            newSizesNames.add(size.getName());
        for(Size size:existingAvailableSizes){
            if(!newSizesNames.contains(size.getName())){
                sizeService.deleteSize(size.getId());
            }
            else{
                existingSizesNames.put(size.getName(),size);
            }
        }
        for(Size size:availableSizes){
            if(!existingSizesNames.containsKey(size.getName())){
                updatedAvailableSizes.add(sizeService.addSize(size));
            }
            else{
                updatedAvailableSizes.add(sizeService.updateSize(size,existingSizesNames.get(size.getName()).getId()));
            }
        }
        return updatedAvailableSizes;
    }

    private List<ProductDetail> updatedProductDetails(List<ProductDetail> productDetails, List<ProductDetail> existingProductDetails) {
        List<ProductDetail> updatedProductDetails=new ArrayList<>();
        HashMap<String,ProductDetail> existingProductDetailNames=new HashMap<>();
        HashMap<String,ProductDetail> newProductDetailNames=new HashMap<>();
        for(ProductDetail productDetail:productDetails)
            newProductDetailNames.put(productDetail.getName(),productDetail);
        for(ProductDetail productDetail:existingProductDetails){
            if(!newProductDetailNames.containsKey(productDetail.getName())){
                productDetailRepository.deleteById(productDetail.getId());
            }
            else{
                existingProductDetailNames.put(productDetail.getName(),productDetail);
            }
        }
        for(ProductDetail productDetail:productDetails){
            if(!existingProductDetailNames.containsKey(productDetail.getName())){
                updatedProductDetails.add(productDetailRepository.save(productDetail));
            }
            else{
                ProductDetail oldProductDetail=existingProductDetailNames.get(productDetail.getName());
                if(!oldProductDetail.getValue().equals(productDetail.getValue())){
                    oldProductDetail.setValue(productDetail.getValue());
                }
                updatedProductDetails.add(oldProductDetail);
            }
        }
        return updatedProductDetails;
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
        if (Objects.isNull(brandRepository.findByName(brand.getName())))
                brandRepository.save(brand);
        return brandRepository.findByName(brand.getName());
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

    private void updateAndSaveTransientObjects(Product product,Product resultantProduct) {
         /* Regarding builder : did not use it, as we are only setting some attributes which need
                                to be saved before saving the product. If we use builder we have
                                set all the attributes of the product.
        */

        resultantProduct.setTaxes(updateAndSaveTaxes(product.getTaxes()));
        resultantProduct.setType(updateAndSaveType(product.getType()));
        resultantProduct.setBrand(updateAndSaveBrand(product.getBrand()));
        resultantProduct.setDiscount(updateAndSaveDiscount(product.getDiscount()));

        resultantProduct.setQuantity(calculateTotalQuantityFromSizes(product.getAvailableSizes()));
        resultantProduct.setMaximumRetailPrice(calculateMaximumRetailPrice(product.getTaxes(),product.getPrice()));
        resultantProduct.setFinalDiscountedPrice(calculateFinalDiscountedPrice(product.getDiscount(),product.getPrice()));
    }

}
