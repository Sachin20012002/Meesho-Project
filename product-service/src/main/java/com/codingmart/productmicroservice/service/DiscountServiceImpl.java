package com.codingmart.productmicroservice.service;

import com.codingmart.productmicroservice.entity.Discount;
import com.codingmart.productmicroservice.exception.NotFoundException;
import com.codingmart.productmicroservice.repository.DiscountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class DiscountServiceImpl implements DiscountService{

    private final DiscountRepository discountRepository;

    @Autowired
    public DiscountServiceImpl(DiscountRepository discountRepository){
        this.discountRepository=discountRepository;
    }
    @Override
    public Discount addDiscount(Discount discount) {
        return discountRepository.save(discount);
    }

    @Override
    public List<Discount> getAllDiscounts() {
        List<Discount> discounts=discountRepository.findAll();
        if(discounts.isEmpty()){
            throw new NotFoundException("discounts are not found");
        }
        return discounts;
    }

    @Override
    public Discount getDiscountById(Long id) {
        if(discountRepository.findById(id).isEmpty()){
            throw new NotFoundException("Discount Id not Found");
        }
        return discountRepository.findById(id).get();
    }

    @Override
    public String deleteDiscount(Long id) {
        if(discountRepository.findById(id).isEmpty()){
            throw new NotFoundException("Discount Id not Found");
        }
        discountRepository.deleteById(id);
        return "Discount deleted Successfully";
    }

    @Override
    public Discount updateDiscount(Discount discount,Long id) {
        if(discountRepository.findById(id).isEmpty()){
            throw new NotFoundException("Discount Id not Found");
        }
        Discount existingDiscount=discountRepository.findById(id).get();
        if(Objects.nonNull(discount.getName()) && !"".equals(discount.getName())){
            existingDiscount.setName(discount.getName());
        }
        if(Objects.nonNull(discount.getActive())){
            existingDiscount.setActive(discount.getActive());
        }
        if(Objects.nonNull(discount.getPercent())){
            existingDiscount.setPercent(discount.getPercent());
        }
        return discountRepository.save(existingDiscount);
    }

    @Override
    public Discount getDiscountByName(String name) {
        if(Objects.isNull(discountRepository.findByName(name))){
            throw new NotFoundException("Discount Name not Found");
        }
        return discountRepository.findByName(name);
    }
}
