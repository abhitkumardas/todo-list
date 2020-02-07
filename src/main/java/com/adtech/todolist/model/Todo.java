package com.adtech.todolist.model;

import com.adtech.todolist.codetype.TodoStatus;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity(name = "todo_todo")
public class Todo {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "TODO_SEQ")
    @SequenceGenerator(name = "TODO_SEQ", sequenceName = "TODO_SEQ")
    @Column(nullable = false)
    private Long todoId;

    @NotNull(message = "Todo is mandatory")
    @Column(nullable = false)
    private String todo;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TodoStatus status; //ACTIVE, COMPLETED, DELETED

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "noteGroupId", nullable = false, referencedColumnName = "noteGroupId")
    private NoteGroup noteGroup;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userId", nullable = false, referencedColumnName = "userId")
    private User user;

    @CreatedDate
    @Column(name = "created_date")
    private Date createdDate;

    @Column
    @LastModifiedDate
    private Date lastModifiedDate;


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

    public NoteGroup getNoteGroup() {
        return noteGroup;
    }

    public void setNoteGroup(NoteGroup noteGroup) {
        this.noteGroup = noteGroup;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public Date getLastModifiedDate() {
        return lastModifiedDate;
    }

    public void setLastModifiedDate(Date lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
    }
}
