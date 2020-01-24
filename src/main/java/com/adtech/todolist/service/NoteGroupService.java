package com.adtech.todolist.service;

import com.adtech.todolist.model.NoteGroup;
import com.adtech.todolist.model.User;

import java.util.List;

public interface NoteGroupService {
    public List<NoteGroup> findAll();
    public NoteGroup findByNoteGroupId(Long noteGroupId);
    public List<NoteGroup> findByUser(User user);
}
