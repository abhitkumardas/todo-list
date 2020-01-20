package com.adtech.todolist.webservices;


import com.adtech.todolist.codetype.TodoStatus;
import com.adtech.todolist.model.Todo;
import com.adtech.todolist.repository.TodoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
public class TodoResource {
    @Autowired
    TodoRepository todoRepository;

    @RequestMapping(value = "/createTodo", method = RequestMethod.POST)
    public void createTodo(@RequestBody Todo todo) {
        todo.setStatus(TodoStatus.ACTIVE);
        todoRepository.save(todo);
    }

    @RequestMapping(value = "/updateTodoStatus/{todoId}", method = RequestMethod.PUT)
    public void updateStatus(@PathVariable("todoId") String todoId) {
        Todo todo = todoRepository.findByTodoId(Long.parseLong(todoId));
        todo.setTodo(todo.getTodo());
        todo.setTodoId(todo.getTodoId());
        todo.setStatus(TodoStatus.COMPLETED);
        todoRepository.save(todo);
    }

    @RequestMapping(value = "/deletTodo/{todoId}", method = RequestMethod.DELETE)
    public void deleteTodo(@PathVariable("todoId") String todoId) {
        Todo todo = todoRepository.findByTodoId(Long.parseLong(todoId));
        todoRepository.delete(todo);
    }

}
