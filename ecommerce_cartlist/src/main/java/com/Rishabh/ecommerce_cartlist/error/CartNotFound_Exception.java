package com.Rishabh.ecommerce_cartlist.error;

public class CartNotFound_Exception extends Exception{
    public CartNotFound_Exception() {
        super();
    }

    public CartNotFound_Exception(String message) {
        super(message);
    }

    public CartNotFound_Exception(String message, Throwable cause) {
        super(message, cause);
    }

    public CartNotFound_Exception(Throwable cause) {
        super(cause);
    }

    protected CartNotFound_Exception(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
