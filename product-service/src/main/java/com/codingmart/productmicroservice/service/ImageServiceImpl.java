package com.codingmart.productmicroservice.service;

import com.codingmart.productmicroservice.entity.Image;
import com.codingmart.productmicroservice.exception.NotFoundException;
import com.codingmart.productmicroservice.repository.ImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class ImageServiceImpl implements ImageService{

    ImageRepository imageRepository;

    @Autowired
    public ImageServiceImpl(ImageRepository imageRepository){
        this.imageRepository=imageRepository;
    }
    @Override
    public Image updateImage(Image image,Long id) {
        if(imageRepository.findById(id).isEmpty()){
            throw new NotFoundException("Image Id not Found");
        }
        Image existingImage=imageRepository.findById(id).get();
        if(Objects.nonNull(image.getName()) && !"".equals(image.getName())){
            existingImage.setName(image.getName());
        }
        if(Objects.nonNull(image.getActive())){
            existingImage.setActive(image.getActive());
        }
        if(Objects.nonNull(image.getUrl()) && !"".equals(image.getUrl())){
            existingImage.setUrl(image.getUrl());
        }
        return imageRepository.save(existingImage);
    }

    @Override
    public String deleteImage(Long id) {
        if(imageRepository.findById(id).isEmpty()){
            throw new NotFoundException("Image Id not Found");
        }
        imageRepository.deleteById(id);
        return "Image deleted Successfully";
    }

    @Override
    public Image getImage(Long id) {
        if(imageRepository.findById(id).isEmpty()){
            throw new NotFoundException("Image Id not Found");
        }
        return imageRepository.findById(id).get();
    }

    @Override
    public List<Image> getAllImages() {
        List<Image> images=imageRepository.findAll();
        if(images.isEmpty()){
            throw new NotFoundException("Images are not found");
        }
        return images;
    }

    @Override
    public Image addImage(Image image) {
        return imageRepository.save(image);
    }
}
