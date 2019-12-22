package com.adtech.todolist.model;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "todo_users")
@EntityListeners(AuditingEntityListener.class)
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "USER_SEQ")
    @SequenceGenerator(name = "USER_SEQ", sequenceName = "USER_SEQ")
    @Column(name = "user_id", nullable = false, updatable = false)
    private Long userId;

    @Column(name = "user_name")
    private String userName;

    @Column(name = "guid")
    private String guid;

    @CreatedDate
    @Column(name = "create_date")
    private Date createDate;

    @Column(name = "email")
    private String email;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getGuid() {
        return guid;
    }

    public void setGuid(String guid) {
        this.guid = guid;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public User(Long userId, String userName, String guid, Date createDate, String email) {
        this.userId = userId;
        this.userName = userName;
        this.guid = guid;
        this.createDate = createDate;
        this.email = email;
    }

    public User() {
    }
}
