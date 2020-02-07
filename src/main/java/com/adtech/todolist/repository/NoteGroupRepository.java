package com.adtech.todolist.repository;

import com.adtech.todolist.model.NoteGroup;
import com.adtech.todolist.model.Todo;
import com.adtech.todolist.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NoteGroupRepository extends JpaRepository<NoteGroup,Long> {
    public List<NoteGroup> findAll();
    public NoteGroup findByNoteGroupId(Long noteGroupId);
    public List<NoteGroup> findByUser(User user);
}
