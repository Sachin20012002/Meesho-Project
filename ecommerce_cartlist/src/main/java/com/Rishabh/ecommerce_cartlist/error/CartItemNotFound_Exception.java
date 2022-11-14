package com.Rishabh.ecommerce_cartlist.error;

public class CartItemNotFound_Exception extends Exception{

    public CartItemNotFound_Exception() {
        super();
    }

    public CartItemNotFound_Exception(String message) {
        super(message);
    }

    public CartItemNotFound_Exception(String message, Throwable cause) {
        super(message, cause);
    }

    public CartItemNotFound_Exception(Throwable cause) {
        super(cause);
    }

    protected CartItemNotFound_Exception(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
