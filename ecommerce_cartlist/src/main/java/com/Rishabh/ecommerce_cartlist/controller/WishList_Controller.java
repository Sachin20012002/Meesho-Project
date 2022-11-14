package com.Rishabh.ecommerce_cartlist.controller;

import com.Rishabh.ecommerce_cartlist.common.ApiResponse;
import com.Rishabh.ecommerce_cartlist.entity.WishList;
import com.Rishabh.ecommerce_cartlist.error.WishListNotFound_Exception;
import com.Rishabh.ecommerce_cartlist.service.WishList_Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class WishList_Controller {
    private final WishList_Service wishList_service;
    @Autowired
    public WishList_Controller(WishList_Service wishList_service) {
        this.wishList_service = wishList_service;
    }

    @PostMapping("/Wishlist")
    public ApiResponse saveWishList(@RequestBody WishList wishList){
        return wishList_service.saveWishList(wishList);
    }
    @GetMapping("/Wishlist")
    public ApiResponse fetchWishList(){
        return wishList_service.fetchWishItemList();
    }
    @GetMapping("/Wishlist/{id}")
    public ApiResponse fetchWishListById(@PathVariable("id") Long id) throws WishListNotFound_Exception {
        return wishList_service.fetchWishListById(id);
    }
    @DeleteMapping("/Wishlist/{id}")
    public ApiResponse deleteWishListById(@PathVariable("id") Long id){
        return wishList_service.deleteWishListById(id);
    }
    @DeleteMapping("/Wishlist")
    public ApiResponse deleteWishList(){
        return wishList_service.deleteWishList();
    }
    @PutMapping("/Wishlist/{id}")
    public ApiResponse updateWishList(@PathVariable("id") Long id,@RequestBody WishList wishList){
        return wishList_service.updateWishList(id,wishList);
    }

}
