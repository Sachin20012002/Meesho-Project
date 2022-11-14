package com.Rishabh.ecommerce_cartlist.error;

public class WishListNotFound_Exception extends Exception{
    public WishListNotFound_Exception() {
        super();
    }

    public WishListNotFound_Exception(String message) {
        super(message);
    }

    public WishListNotFound_Exception(String message, Throwable cause) {
        super(message, cause);
    }

    public WishListNotFound_Exception(Throwable cause) {
        super(cause);
    }

    protected WishListNotFound_Exception(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
