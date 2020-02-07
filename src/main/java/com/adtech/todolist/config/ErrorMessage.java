package com.adtech.todolist.config;

import com.adtech.todolist.codetype.InfoType;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import org.springframework.http.HttpStatus;

import java.util.Collections;
import java.util.List;

@JsonPropertyOrder({ "data", "message", "infoType", "status", "statusCode", "errors"})
public class ErrorMessage extends Message {

    private Object data;

    private List<String> errors;

    private ErrorMessage() {
    }

    public ErrorMessage(Object data, String message, InfoType infoType, HttpStatus status) {
        super(message, infoType, status);
        this.data = data;
    }

    public ErrorMessage(String message, InfoType infoType, HttpStatus status) {
        super(message, infoType, status);
        this.data = null;
    }

    public ErrorMessage(String message, InfoType infoType, HttpStatus status, List<String> errors) {
        super(message, infoType, status);
        this.errors = errors;
        this.data = null;
    }

    public ErrorMessage(String message, InfoType infoType, HttpStatus status, String error) {
        super(message, infoType, status);
        this.errors = Collections.singletonList(error);
        this.data = null;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public List<String> getErrors() {
        return errors;
    }

    public void setErrors(List<String> errors) {
        this.errors = errors;
    }
}
