package com.adtech.todolist.model.request;

import java.io.Serializable;

/**
 * @author ferozk
 */
public class TodoReq implements Serializable {
    private String userId;
    private String todo;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getTodo() {
        return todo;
    }

    public void setTodo(String todo) {
        this.todo = todo;
    }
}
