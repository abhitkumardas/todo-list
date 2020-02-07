/*
package com.adtech.todolist.extras;


import com.adtech.todolist.model.Todo;
import com.adtech.todolist.model.User;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "user_todo_list")
public class UserTodoList {
    @Id
    @Column(name = "user_todo_id")
    private Long userTodoId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false, referencedColumnName = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "todo_id", nullable = false, referencedColumnName = "todo_id")
    private Todo todoList;

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

    public Todo getTodoList() {
        return todoList;
    }

    public void setTodoList(Todo todoList) {
        this.todoList = todoList;
    }
}
*/
