package com.Rishabh.ecommerce_cartlist.service;

import com.Rishabh.ecommerce_cartlist.common.ApiResponse;
import com.Rishabh.ecommerce_cartlist.entity.Cart;
import com.Rishabh.ecommerce_cartlist.entity.CartItem;
import com.Rishabh.ecommerce_cartlist.error.CartItemNotFound_Exception;
import com.Rishabh.ecommerce_cartlist.error.CartNotFound_Exception;
import com.Rishabh.ecommerce_cartlist.repository.CartItem_Repository;
import com.Rishabh.ecommerce_cartlist.repository.Cart_Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.*;

@Service
public class CartItem_Service_Impl implements CartItem_Service{
    private final CartItem_Repository cartItem_repository;
    private final Cart_Repository cart_repository;
    @Autowired
    public CartItem_Service_Impl(CartItem_Repository cartItem_repository, Cart_Repository cart_repository) {
        this.cartItem_repository = cartItem_repository;
        this.cart_repository = cart_repository;
    }

    @Override
    public ApiResponse saveCartItem(CartItem cartItem, Long cartId) throws CartNotFound_Exception {
        if(cart_repository.existsById(cartId)==false){
            throw new CartNotFound_Exception("Cart for this id doesn't exist");
        }
        cartItem.setCartDiscount((15* cartItem.getProductPrice())/100);
        cartItem.setTotalPrice(cartItem.getProductPrice()-cartItem.getCartDiscount()+cartItem.getDeliveryFees());
        LocalDateTime d=LocalDateTime.now();
        cartItem.setCreatedOn(d);
        Random random=new Random();
        int s=random.nextInt(100);
        cartItem.setProductId(String.valueOf(d)+"-"+String.valueOf(s));
        ApiResponse apiResponse=new ApiResponse();
        cartItem=cartItem_repository.save(cartItem);
        Cart cart=cart_repository.findById(cartId).get();
        cart.getCartItems().add(cartItem);
        cart_repository.save(cart);
        apiResponse.setData(cartItem);
        return apiResponse;
    }

    @Override
    public ApiResponse fetchCartItemList() throws CartItemNotFound_Exception {
        ApiResponse apiResponse=new ApiResponse();
        List<CartItem> list=cartItem_repository.findAll();
        if(list.isEmpty()){
            throw new CartItemNotFound_Exception("Cart does not contain any items");
        }
        apiResponse.setData(list);
        return apiResponse;
    }

    @Override
    public ApiResponse fetchCartItemListById(Long id) throws CartItemNotFound_Exception {
        ApiResponse apiResponse=new ApiResponse();
        Optional<CartItem> cartItem=cartItem_repository.findById(id);
        apiResponse.setData(cartItem);
        if(!cartItem.isPresent()){
            throw new CartItemNotFound_Exception("CartItem not available");
        }
        return apiResponse;
    }

    @Override
    public ApiResponse deleteCartItemsById(Long id) throws CartItemNotFound_Exception {
        if(cartItem_repository.existsById(id)==false){
            throw new CartItemNotFound_Exception("item does not exist");
        }
        ApiResponse apiResponse=new ApiResponse();
        cartItem_repository.deleteById(id);
        String s="Record deleted successfully";
        apiResponse.setData(id+" "+s);
        return apiResponse;
    }

    @Override
    public ApiResponse deleteCartItems() throws CartItemNotFound_Exception {
        if(cartItem_repository.findAll().isEmpty()){
            throw new CartItemNotFound_Exception("Cart-items are already empty");
        }
        ApiResponse apiResponse=new ApiResponse();
        cartItem_repository.deleteAll();
        String s="All records deleted successfully";
        apiResponse.setData(s);
        return apiResponse;
    }

    @Override
    public ApiResponse updateCartItem(Long id, CartItem cartItem) {
        ApiResponse apiResponse=new ApiResponse();
        LocalDateTime u=LocalDateTime.now();
        CartItem cartDB=cartItem_repository.findById(id).get();
        cartDB.setUpdatedOn(u);
        if(Objects.nonNull(cartItem.getProductId())){
            cartDB.setProductId(cartItem.getProductId());
        }
        if(Objects.nonNull(cartItem.getProductPrice())){
            cartDB.setProductPrice(cartItem.getProductPrice());
        }
        if(Objects.nonNull(cartItem.getQuantity())){
            cartDB.setQuantity(cartItem.getQuantity());
        }
        if(Objects.nonNull(cartItem.getDeliveryFees())){
            cartDB.setDeliveryFees(cartItem.getDeliveryFees());
        }
        if(Objects.nonNull(cartItem.getSupplierName())&& !"".equalsIgnoreCase(cartItem.getSupplierName())){
            cartDB.setSupplierName(cartItem.getSupplierName());
        }
        cartDB.setCartDiscount((15*cartDB.getProductPrice())/100);
        cartDB.setTotalPrice(cartDB.getProductPrice()-cartDB.getCartDiscount()+cartDB.getDeliveryFees());
        cartDB=cartItem_repository.save(cartDB);
        apiResponse.setData(cartDB);
        return apiResponse;
    }


}
