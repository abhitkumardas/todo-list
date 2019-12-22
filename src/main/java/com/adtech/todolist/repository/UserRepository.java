package com.adtech.todolist.repository;

import com.adtech.todolist.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {
    public List<User> findAll();
    public User findByUserId(String userId);

}
