package com.codingmart.productmicroservice.service;

import com.codingmart.productmicroservice.entity.Size;
import com.codingmart.productmicroservice.exception.NotFoundException;
import com.codingmart.productmicroservice.repository.SizeRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class SizeServiceImpl implements SizeService{

    SizeRepository sizeRepository;

    public SizeServiceImpl(SizeRepository sizeRepository){
        this.sizeRepository=sizeRepository;
    }
    @Override
    public Size addSize(Size size) {
        return sizeRepository.save(size);
    }



    @Override
    public List<Size> getAllSizes() {
        List<Size> sizes=sizeRepository.findAll();
        return sizes;
    }

    @Override
    public Size getSize(Long id) {
        if(sizeRepository.findById(id).isEmpty()){
            throw new NotFoundException("Size Id not Found");
        }
        return sizeRepository.findById(id).get();
    }

    @Override
    public String deleteSize(Long id) {
        if(sizeRepository.findById(id).isEmpty()){
            throw new NotFoundException("Size Id not Found");
        }
        sizeRepository.deleteById(id);
        return "Size deleted successfully";
    }

    @Override
    public Size updateSize(Size size,Long id) {
        if(sizeRepository.findById(id).isEmpty()){
            throw new NotFoundException("Size Id not Found");
        }
        Size existingSize=sizeRepository.findById(id).get();
        if(Objects.nonNull(size.getName()) && !"".equals(size.getName())){
            existingSize.setName(size.getName());
        }
        if(Objects.nonNull(size.getQuantity())){
            existingSize.setQuantity(size.getQuantity());
        }
        if(Objects.nonNull(size.getActive())){
            existingSize.setActive(size.getActive());
        }
        return sizeRepository.save(existingSize);
    }
}
