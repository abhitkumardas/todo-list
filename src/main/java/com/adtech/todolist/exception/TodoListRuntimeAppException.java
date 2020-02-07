package com.adtech.todolist.exception;

import com.adtech.todolist.codetype.InfoType;
import com.adtech.todolist.config.ErrorMessage;
import org.springframework.http.HttpStatus;

public class TodoListRuntimeAppException extends RuntimeException{

    private ErrorMessage errorMessage;

    public TodoListRuntimeAppException(String message, InfoType notificationinfo, HttpStatus status) {
        super(message);
        this.errorMessage = new ErrorMessage(message, notificationinfo, status);
    }

    public ErrorMessage getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(ErrorMessage errorMessage) {
        this.errorMessage = errorMessage;
    }
}
