package com.adtech.todolist.exception;

import com.adtech.todolist.codetype.InfoType;
import com.adtech.todolist.config.ErrorMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;


public class TodoListAuthException extends RuntimeException {

    @Autowired
    private ErrorMessage errorMessage;

    public TodoListAuthException(String message) {
        super(message);
        this.errorMessage = new ErrorMessage(message, InfoType.ERROR, HttpStatus.FORBIDDEN);
    }

    public ErrorMessage getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(ErrorMessage errorMessage) {
        this.errorMessage = errorMessage;
    }
}