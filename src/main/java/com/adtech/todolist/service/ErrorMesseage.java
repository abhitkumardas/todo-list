package com.adtech.todolist.service;


import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;


public class ErrorMesseage extends Message {

    private Object data;

    public ErrorMesseage(Object data, String message, HttpStatus statusCode) {
        super(message, statusCode);
        this.data = data;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
