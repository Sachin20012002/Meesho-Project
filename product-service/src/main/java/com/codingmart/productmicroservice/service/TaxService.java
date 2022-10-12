package com.codingmart.productmicroservice.service;

import com.codingmart.productmicroservice.entity.Tax;

import java.util.List;

public interface TaxService {
    Tax addTax(Tax tax);

    Tax getTaxByName(String name);

    Tax updateTax(Tax tax,Long id);

    Tax getTax(Long id);

    String deleteTax(Long id);

    List<Tax> getAllTaxes();
}
