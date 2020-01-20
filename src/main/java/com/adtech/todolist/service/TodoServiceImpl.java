package com.adtech.todolist.service;

import com.adtech.todolist.model.Todo;
import com.adtech.todolist.repository.TodoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TodoServiceImpl implements TodoService {

    @Autowired
    TodoRepository todoRepository;

    @Override
    public Todo getByTodoId(Long todoId) {
        return null;
    }
}
