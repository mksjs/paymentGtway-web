package com.paymentGtway.paymentGtwayweb.model;

/**
 * @author manish
 *
 * @param <T>
 */
public class RestResponse<T> {
    private int status;
    private String message;
    T data;

    public RestResponse(T data) {
        this.data = data;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
