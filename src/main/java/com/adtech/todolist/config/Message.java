package com.adtech.todolist.config;

import com.adtech.todolist.codetype.InfoType;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.http.HttpStatus;

import java.io.Serializable;

public abstract class Message implements Serializable {

    private String message;

    private InfoType infoType;

    @JsonIgnore
    private HttpStatus status;

    private int statusCode;

    public Message(){

    }

    public Message(String message, InfoType infoType, HttpStatus status) {
        this.message = message;
        this.infoType = infoType;
        this.status = status;
        this.statusCode = status.value();
    }

    public HttpStatus getStatus() {
        return status;
    }

    public void setStatus(HttpStatus status) {
        this.status = status;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public InfoType getInfoType() {
        return infoType;
    }

    public void setInfoType(InfoType infoType) {
        this.infoType = infoType;
    }

}
