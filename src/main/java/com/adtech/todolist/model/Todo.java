package com.adtech.todolist.model;

import com.adtech.todolist.codetype.TodoStatus;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@JsonIgnoreProperties({"user"})
public class Todo implements Serializable {
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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false, referencedColumnName = "user_id")
    @JsonProperty("user")
    private User user;

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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

}
