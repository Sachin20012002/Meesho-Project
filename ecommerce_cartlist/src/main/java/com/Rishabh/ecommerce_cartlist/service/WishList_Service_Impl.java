package com.Rishabh.ecommerce_cartlist.service;

import com.Rishabh.ecommerce_cartlist.common.ApiResponse;
import com.Rishabh.ecommerce_cartlist.entity.WishList;
import com.Rishabh.ecommerce_cartlist.error.WishListNotFound_Exception;
import com.Rishabh.ecommerce_cartlist.repository.WishList_Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class WishList_Service_Impl implements WishList_Service{
    private final WishList_Repository wishList_repository;
    @Autowired
    public WishList_Service_Impl(WishList_Repository wishList_repository) {
        this.wishList_repository = wishList_repository;
    }

    @Override
    public ApiResponse saveWishList(WishList wishList) {
        LocalDateTime d=LocalDateTime.now();
        wishList.setCreated_on(d);
        wishList.setProduct_id(String.valueOf(d));
        wishList.setCustomer_id(String.valueOf(d));
        ApiResponse apiResponse=new ApiResponse();
        wishList=wishList_repository.save(wishList);
        apiResponse.setData(wishList);
       return apiResponse;
    }

    @Override
    public ApiResponse fetchWishItemList() {
        ApiResponse apiResponse=new ApiResponse();
        List<WishList> list=wishList_repository.findAll();
        apiResponse.setData(list);
        return apiResponse;
    }

    @Override
    public ApiResponse fetchWishListById(Long id) throws WishListNotFound_Exception {
        ApiResponse apiResponse=new ApiResponse();
        Optional<WishList> wishList=wishList_repository.findById(id);
        if(!wishList.isPresent()){
            throw new WishListNotFound_Exception("Item not found in wishlist");
        }
        apiResponse.setData(wishList);
        return apiResponse;
    }

    @Override
    public ApiResponse deleteWishListById(Long id) {
        ApiResponse apiResponse=new ApiResponse();
        wishList_repository.deleteById(id);
        String s="is removed from wishlist";
        apiResponse.setData(id+" "+s);
        return apiResponse;
    }

    @Override
    public ApiResponse deleteWishList() {
        ApiResponse apiResponse=new ApiResponse();
        wishList_repository.deleteAll();
        String s="All Items are removed from wishlist";
        apiResponse.setData(s);
        return apiResponse;
    }

    @Override
    public ApiResponse updateWishList(Long id, WishList wishList) {
        ApiResponse apiResponse=new ApiResponse();
        LocalDateTime u=LocalDateTime.now();
        WishList wishDB=wishList_repository.findById(id).get();
        wishDB.setUpdated_on(u);
        if(Objects.nonNull(wishList.getProduct_id())){
            wishDB.setProduct_id(wishList.getProduct_id());
        }
        if(Objects.nonNull(wishList.getCustomer_id())){
            wishDB.setCustomer_id(wishList.getCustomer_id());
        }
        if(Objects.nonNull(wishList.getProduct_price())){
            wishDB.setProduct_price(wishList.getProduct_price());
        }
        wishDB=wishList_repository.save(wishDB);
        apiResponse.setData(wishDB);
        return apiResponse;
    }
}
