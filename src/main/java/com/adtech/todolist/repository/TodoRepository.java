package com.adtech.todolist.repository;

import com.adtech.todolist.model.Todo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TodoRepository extends JpaRepository<Todo,Long> {
    public List<Todo> findAll();
    public Todo findByTodoId(Long todoId);


}
