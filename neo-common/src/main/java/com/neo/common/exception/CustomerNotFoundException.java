package com.neo.common.exception;

/**
 * @Author ABODE
 * @Date 2025/03/09 9:14 AM
 */
public class CustomerNotFoundException extends RuntimeException {
    public CustomerNotFoundException(String message) {
        super(message);
    }
}
