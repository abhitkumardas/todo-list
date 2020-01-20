package com.adtech.todolist.model;

import com.adtech.todolist.codetype.TodoStatus;

import javax.persistence.*;

@Entity
public class Todo {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "TODO_SEQ")
    @SequenceGenerator(name = "TODO_SEQ", sequenceName = "TODO_SEQ")
    @Column(name = "todo_id", nullable = false)
    private Long todoId;

    @Column(nullable = false)
    private String todo;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TodoStatus status; //ACTIVE, COMPLETED, DELETED

    public Long getTodoId() {
        return todoId;
    }

    public void setTodoId(Long todoId) {
        this.todoId = todoId;
    }

    public String getTodo() {
        return todo;
    }

    public void setTodo(String todo) {
        this.todo = todo;
    }

    public TodoStatus getStatus() {
        return status;
    }

    public void setStatus(TodoStatus status) {
        this.status = status;
    }
}
