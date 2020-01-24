package com.adtech.todolist.service;

import com.adtech.todolist.model.NoteGroup;
import com.adtech.todolist.model.Todo;
import com.adtech.todolist.model.User;

import java.util.List;

public interface TodoService {
    public List<Todo> findAll();
    public Todo getByTodoId(Long todoId);
    public List<Todo> findByNoteGroupId(NoteGroup noteGroup);
    public List<Todo> findByUser(User user);
    public List<Todo> findByUserAndNoteGroup(User user, NoteGroup noteGroup);
}
