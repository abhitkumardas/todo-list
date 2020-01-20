package com.adtech.todolist.service;

import com.adtech.todolist.model.Todo;

public interface TodoService {
    public Todo getByTodoId(Long todoId);
}
