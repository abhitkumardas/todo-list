package com.adtech.todolist.model.Request;

import javax.validation.constraints.NotNull;

public class UserRegisterRequest {

    @NotNull
    private String userName;
}
