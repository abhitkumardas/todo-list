package com.adtech.todolist.service;

import com.adtech.todolist.model.NoteGroup;
import com.adtech.todolist.model.User;
import com.adtech.todolist.repository.NoteGroupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NoteGroupServiceImpl implements NoteGroupService{

    @Autowired
    NoteGroupRepository noteGroupRepository;

    @Override
    public List<NoteGroup> findAll(){
        return noteGroupRepository.findAll();
    }

    @Override
    public NoteGroup findByNoteGroupId(Long noteGroupId){
        return noteGroupRepository.findByNoteGroupId(noteGroupId);
    }

    @Override
    public List<NoteGroup> findByUser(User user){
        return noteGroupRepository.findByUser(user);
    }
}
