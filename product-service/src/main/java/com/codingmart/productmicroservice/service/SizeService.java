package com.codingmart.productmicroservice.service;

import com.codingmart.productmicroservice.entity.Size;

import java.util.List;

public interface SizeService {


    Size addSize(Size size);

    List<Size> getAllSizes();

    Size getSize(Long id);

    String deleteSize(Long id);

    Size updateSize(Size size,Long id);
}
