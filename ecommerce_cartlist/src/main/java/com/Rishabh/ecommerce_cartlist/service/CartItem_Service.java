package com.Rishabh.ecommerce_cartlist.service;

import com.Rishabh.ecommerce_cartlist.common.ApiResponse;
import com.Rishabh.ecommerce_cartlist.entity.CartItem;
import com.Rishabh.ecommerce_cartlist.error.CartItemNotFound_Exception;
import com.Rishabh.ecommerce_cartlist.error.CartNotFound_Exception;


public interface CartItem_Service {
    public ApiResponse saveCartItem(CartItem cartItem, Long cartId) throws CartNotFound_Exception;

    public ApiResponse fetchCartItemList() throws CartItemNotFound_Exception;

    public ApiResponse fetchCartItemListById(Long id) throws CartItemNotFound_Exception;

    public ApiResponse deleteCartItemsById(Long id) throws CartItemNotFound_Exception;

    public ApiResponse deleteCartItems() throws CartItemNotFound_Exception;

    public ApiResponse updateCartItem(Long id, CartItem cartItem);


}
