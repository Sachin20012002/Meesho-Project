package com.Rishabh.ecommerce_cartlist.service;

import com.Rishabh.ecommerce_cartlist.common.ApiResponse;
import com.Rishabh.ecommerce_cartlist.entity.Cart;
import com.Rishabh.ecommerce_cartlist.error.CartNotFound_Exception;

import java.util.List;

public interface Cart_Service {
    public ApiResponse saveCart(Cart cart);

    public ApiResponse fetchCartList() throws CartNotFound_Exception;

    public ApiResponse fetchCartListById(Long cart_id) throws CartNotFound_Exception;

    public ApiResponse deleteCart() throws CartNotFound_Exception;

    public ApiResponse deleteCartById(Long cart_id) throws CartNotFound_Exception;
}
