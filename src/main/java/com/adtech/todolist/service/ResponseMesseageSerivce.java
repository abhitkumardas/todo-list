package com.adtech.todolist.service;


import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

@Component
public class ResponseMesseageSerivce {

    public ResponseMessage generateResponseMessage(Object data, String messageKey, HttpStatus status) {
        return new ResponseMessage(data, messageKey, status);
    }

    public ErrorMesseage generateErrorMesseage(Object data, String messeageKey, HttpStatus status) {
        return new ErrorMesseage(data, messeageKey, status);
    }

}
