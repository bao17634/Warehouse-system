package com.pro.warehouse.myexception;

/**
 * 定义仓库异常类
 */
public class StoreException extends Exception{

    public StoreException(String message) {
        super(message);
    }

    public StoreException(String message, Throwable throwable){
        super(message,throwable);
    }
}
