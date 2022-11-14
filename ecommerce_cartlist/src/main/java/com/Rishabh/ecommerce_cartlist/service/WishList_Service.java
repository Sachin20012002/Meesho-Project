package com.Rishabh.ecommerce_cartlist.service;

import com.Rishabh.ecommerce_cartlist.common.ApiResponse;
import com.Rishabh.ecommerce_cartlist.entity.WishList;
import com.Rishabh.ecommerce_cartlist.error.WishListNotFound_Exception;


public interface WishList_Service {
    public ApiResponse saveWishList(WishList wishList);

    public ApiResponse fetchWishItemList();

    public ApiResponse fetchWishListById(Long id) throws WishListNotFound_Exception;

    public ApiResponse deleteWishListById(Long id);

    public ApiResponse deleteWishList();

    public ApiResponse updateWishList(Long id, WishList wishList);
}
