package com.adtech.todolist.service;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import org.springframework.http.HttpStatus;

@JsonPropertyOrder({"data, messageKey, status"})
public class ResponseMessage extends Message {
    private Object data;

    public ResponseMessage(Object data, String message, HttpStatus status) {
        super(message, status);
        this.data = data;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}

