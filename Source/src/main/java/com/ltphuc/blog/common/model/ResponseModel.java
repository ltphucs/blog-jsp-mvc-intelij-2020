package com.ltphuc.blog.common.model;

public class ResponseModel<T> {
    private T object;

    public ResponseModel() {
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    private boolean status;

    private String message;
    private Exception exception;

    public T getObject() {
        return object;
    }

    public void setObject(T object) {
        this.object = object;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Exception getException() {
        return exception;
    }

    public void setException(Exception exception) {
        this.exception = exception;
    }

    public ResponseModel(T object, boolean status, String message, Exception exception) {
        this.object = object;
        this.status = status;
        this.message = message;
        this.exception = exception;
    }


}
