package com.adtech.todolist.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.data.annotation.Id;

import javax.persistence.*;
import java.util.List;

@Entity(name = "todo_note_group")
public class NoteGroup {

    @Id
    @JsonIgnore
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long noteGroupId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userId", nullable = false, referencedColumnName = "userId")
    private User user;

    @Column(nullable = false)
    private List<Todo> todos;
}
