package com.adtech.todolist.service;

import com.adtech.todolist.codetype.InfoType;
import com.adtech.todolist.config.ErrorMessage;
import com.adtech.todolist.config.ErrorMessages;
import com.adtech.todolist.config.ResponseMessage;
import com.adtech.todolist.config.ResponseMessages;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ResponseMessageService {

    @Autowired
    private ResponseMessages responseMessages;

    @Autowired
    private ErrorMessages errorMessages;

    public ResponseMessage generateResponseMessage(Object data, String messageKey, HttpStatus status) {
        return new ResponseMessage(data,responseMessages.getProperty(messageKey), InfoType.INFO, status);
    }

    public ResponseMessage generateResponseMessage(Object data, String messageKey, List<Object> args, HttpStatus status) {
        return new ResponseMessage(data,responseMessages.getProperty(messageKey, args), InfoType.INFO, status);
    }

    public ResponseMessage generateWarningMessage(Object data,String messageKey, HttpStatus status) {
        return new ResponseMessage(data,responseMessages.getProperty(messageKey), InfoType.WARNING , status);
    }

    public ErrorMessage generateErrorMessage(Object data, String messageKey, HttpStatus status) {
        return new ErrorMessage(data,errorMessages.getProperty(messageKey), InfoType.ERROR , status);
    }

    public ErrorMessage generateErrorMessage(Object data, String messageKey, List<Object> args, HttpStatus status) {
        return new ErrorMessage(data,errorMessages.getProperty(messageKey,args), InfoType.ERROR , status);
    }

}
