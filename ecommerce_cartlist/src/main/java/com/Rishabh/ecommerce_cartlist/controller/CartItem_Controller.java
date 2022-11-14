package com.Rishabh.ecommerce_cartlist.controller;

import com.Rishabh.ecommerce_cartlist.common.ApiResponse;
import com.Rishabh.ecommerce_cartlist.entity.CartItem;
import com.Rishabh.ecommerce_cartlist.error.CartItemNotFound_Exception;
import com.Rishabh.ecommerce_cartlist.error.CartNotFound_Exception;
import com.Rishabh.ecommerce_cartlist.service.CartItem_Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class CartItem_Controller {
    private final CartItem_Service cartItem_service;
    @Autowired
    public CartItem_Controller(CartItem_Service cartItem_service) {
        this.cartItem_service = cartItem_service;
    }
    @PostMapping("/cartitems/{id}")
    public ApiResponse saveCartItem(@RequestBody CartItem cartItem,@PathVariable("id") Long cartId) throws CartNotFound_Exception {
        return cartItem_service.saveCartItem(cartItem,cartId);
    }
    @GetMapping("/cartitems")
    public ApiResponse fetchCartItemList() throws CartItemNotFound_Exception {
        return cartItem_service.fetchCartItemList();
    }
    @GetMapping("/cartitems/{id}")
    public ApiResponse fetchCartItemListById(@PathVariable("id") Long id) throws CartItemNotFound_Exception {
        return cartItem_service.fetchCartItemListById(id);
    }
    @DeleteMapping("/cartitems/{id}")
    public ApiResponse deleteCartItemsById(@PathVariable("id") Long id) throws CartItemNotFound_Exception {
        return cartItem_service.deleteCartItemsById(id);
    }
    @DeleteMapping("/cartitems")
    public ApiResponse deleteCartItems() throws CartItemNotFound_Exception {
        return cartItem_service.deleteCartItems();
    }
    @PutMapping("/cartitems/{id}")
    public ApiResponse updateCartItem(@PathVariable("id") Long id,@RequestBody CartItem cartItem){
        return cartItem_service.updateCartItem(id,cartItem);
    }

}
