package com.adtech.todolist.service;

import org.springframework.http.HttpStatus;

import java.io.Serializable;

public abstract class Message implements Serializable {

    private String message;
    private HttpStatus statusCode;

    public Message(String message, HttpStatus statusCode) {
        this.message = message;
        this.statusCode = statusCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public HttpStatus getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(HttpStatus statusCode) {
        this.statusCode = statusCode;
    }
}
