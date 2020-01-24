package com.adtech.todolist.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "todo_user")
@EntityListeners(AuditingEntityListener.class)
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "USER_SEQ")
    @SequenceGenerator(name = "USER_SEQ", sequenceName = "USER_SEQ")
    @Column(nullable = false, updatable = false)
    private Long userId;

    @Column(nullable = false, unique = true)
    private String guid;

    @Column(nullable = false)
    private Boolean isActive;

    @NotNull(message = "User name is mandatory.")
    @Column(nullable = false, unique = true)
    private String userName;

    @NotNull(message = "User email is mandatory.")
    @Column(nullable = false, unique = true)
    private String email;

    @NotNull(message = "User first name is mandatory.")
    @Column(nullable = false)
    private String firstName;

    @Column
    private String middleName;

    @Column
    private String lastName;

    @NotNull(message = "Password is mandatory.")
    @Column(nullable = false)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @Transient
    private String password;

    /*
    @NotNull(message = "Is Email Verified is mandatory.")
    @Column(nullable = false)
    private Boolean isEmailVerified;

    @NotNull(message = "Is Approved is mandatory.")
    @Column(nullable = false)
    private Boolean isApproved;

    @Column(nullable = false, unique = true)
    private String contactNumber;

    // @NotBlank(message = "Registered By IP is mandatory.")
    @Column(nullable = false, updatable = false)
    private String registeredByIp;

    // @NotBlank(message = "Last Modified By IP is mandatory.")
    @Column(nullable = false)
    private String lastModifiedByIp;

    // @NotBlank(message = "Registered By user agent info is mandatory.")
    @Column(nullable = false, updatable = false)
    private String registeredByUserAgent;

    // @NotBlank(message = "Last Modified By user agent info is mandatory.")
    @Column(nullable = false)
    private String lastModifiedByUserAgent;

    // @NotNull(message = "Registered By app source is mandatory.")
    @Column(nullable = false, updatable = false)
    private String registeredByAppSource;

    // @NotNull(message = "Last Modified By app source is mandatory.")
    @Column(nullable = false)
    private String lastModifiedByAppSource;
    */

    @Column
    private List<NoteGroup> noteGroupList;

    @CreatedDate
    @Column(name = "created_date")
    private Date createdDate;

    @Column
    @LastModifiedDate
    private Date lastModifiedDate;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getGuid() {
        return guid;
    }

    public void setGuid(String guid) {
        this.guid = guid;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<NoteGroup> getNoteGroupList() {
        return noteGroupList;
    }

    public void setNoteGroupList(List<NoteGroup> noteGroupList) {
        this.noteGroupList = noteGroupList;
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
