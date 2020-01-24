package com.adtech.todolist.service;

import com.adtech.todolist.model.NoteGroup;
import com.adtech.todolist.model.Todo;
import com.adtech.todolist.model.User;
import com.adtech.todolist.repository.TodoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TodoServiceImpl implements TodoService {

    @Autowired
    TodoRepository todoRepository;

    @Override
    public List<Todo> findAll(){
        return todoRepository.findAll();
    }

    @Override
    public Todo getByTodoId(Long todoId) {
        return todoRepository.findByTodoId(todoId);
    }

    @Override
    public List<Todo> findByNoteGroupId(NoteGroup noteGroup){
        return todoRepository.findByNoteGroupId(noteGroup);
    }

    @Override
    public List<Todo> findByUser(User user){
        return todoRepository.findByUser(user);
    }

    @Override
    public List<Todo> findByUserAndNoteGroup(User user, NoteGroup noteGroup){
        return todoRepository.findByUserAndNoteGroup(user, noteGroup);
    }
}
