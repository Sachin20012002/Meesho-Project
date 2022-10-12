package com.codingmart.productmicroservice.service;

import com.codingmart.productmicroservice.entity.Type;
import com.codingmart.productmicroservice.exception.NotFoundException;
import com.codingmart.productmicroservice.repository.TypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class TypeServiceImpl implements TypeService{

    TypeRepository typeRepository;

    @Autowired
    public TypeServiceImpl(TypeRepository typeRepository){
        this.typeRepository=typeRepository;
    }
    @Override
    public Type updateType(Type type,Long id) {
        if(typeRepository.findById(id).isEmpty()){
            throw new NotFoundException("Type Id not Found");
        }
        Type existingType=typeRepository.findById(id).get();
        if(Objects.nonNull(type.getName()) && !"".equals(type.getName())){
            existingType.setName(type.getName());
        }
        if(Objects.nonNull(type.getActive())){
            existingType.setActive(type.getActive());
        }
        typeRepository.save(existingType);
        return existingType;
    }

    @Override
    public String deleteType(Long id) {
        if(typeRepository.findById(id).isEmpty()){
            throw new NotFoundException("Type Id not Found");
        }
        typeRepository.deleteById(id);
        return "Type deleted Successfully";
    }

    @Override
    public Type getType(Long id) {
        if(typeRepository.findById(id).isEmpty()){
            throw new NotFoundException("Type Id not Found");
        }
        return typeRepository.findById(id).get();
    }

    @Override
    public List<Type> getAllTypes() {
        List<Type> types=typeRepository.findAll();
        if(types.isEmpty()){
            throw new NotFoundException("Types are not found");
        }
        return types;
    }

    @Override
    public Type addType(Type type) {
        return typeRepository.save(type);
    }

    @Override
    public Type getTypeByName(String name) {
        if(Objects.nonNull(typeRepository.findByName(name))){
            throw new NotFoundException("Type Id not Found");
        }
        return typeRepository.findByName(name);
    }


}
