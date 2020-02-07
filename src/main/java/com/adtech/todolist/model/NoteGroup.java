package com.adtech.todolist.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

@Entity(name = "todo_note_group")
public class NoteGroup {

    @Id
    @JsonIgnore
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long noteGroupId;

    @NotNull(message = "Note Group is mandatory")
    @Column(nullable = false)
    private String noteGroupName;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userId", nullable = false, referencedColumnName = "userId")
    private User user;

    @CreatedDate
    @Column(name = "created_date")
    private Date createdDate;

    @Column
    @LastModifiedDate
    private Date lastModifiedDate;


    public Long getNoteGroupId() {
        return noteGroupId;
    }

    public void setNoteGroupId(Long noteGroupId) {
        this.noteGroupId = noteGroupId;
    }

    public String getNoteGroupName() {
        return noteGroupName;
    }

    public void setNoteGroupName(String noteGroupName) {
        this.noteGroupName = noteGroupName;
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
