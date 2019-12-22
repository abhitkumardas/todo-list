package com.adtech.todolist.model;


import javax.persistence.*;

@Entity
@Table(name = "user_todo_list")
public class UserTodoList {
    @Id
    @Column(name = "user_todo_id")
    private Long userTodoId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false, referencedColumnName = "user_id")
    private User user;

    @Column (length = 25000)
    private String todoList;

    public Long getUserTodoId() {
        return userTodoId;
    }

    public void setUserTodoId(Long userTodoId) {
        this.userTodoId = userTodoId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getTodoList() {
        return todoList;
    }

    public void setTodoList(String todoList) {
        this.todoList = todoList;
    }
}
