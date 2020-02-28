package com.adtech.todolist.repository;

import com.adtech.todolist.model.Todo;
import com.adtech.todolist.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TodoRepository extends JpaRepository<Todo,Long> {
    public List<Todo> findAll();
    List<Object> findByUser(User user);
    Todo findByTodoId(Long todoId);
}
