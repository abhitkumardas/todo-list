/*
package com.adtech.todolist.extras;

import com.adtech.todolist.model.NoteGroup;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

*/
/**
 * @author Abhit Das
 *//*

//@EntityListeners(AuditingEntityListener.class)

@Entity(name = "todo_user")
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
public class EndUser implements Serializable{

    @Id
    @JsonIgnore
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userID;

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



    @Column(nullable = false)
    private List<NoteGroup> noteGroupList;



    @NotNull(message = "Is Email Verified is mandatory.")
    @Column(nullable = false)
    private Boolean isEmailVerified;

    @NotNull(message = "Is Approved is mandatory.")
    @Column(nullable = false)
    private Boolean isApproved;

    @Column
    private String designation;

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

    */
/*
    // @NotNull(message = "Registered By app source is mandatory.")
    @Column(nullable = false, updatable = false)
    private AppSource registeredByAppSource;

    // @NotNull(message = "Last Modified By app source is mandatory.")
    @Column(nullable = false)
    private AppSource lastModifiedByAppSource;
    *//*


    @Column
    @CreatedDate
    private Date createdDate;

    @Column
    @LastModifiedDate
    private Date lastModifiedDate;



}

*/
