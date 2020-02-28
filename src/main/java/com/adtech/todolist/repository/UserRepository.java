package com.adtech.todolist.repository;

import com.adtech.todolist.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {
    public List<User> findAll();
    public User findByUserId(Long userId);
    User findByGuid(String guid);
    User findByEmail(String email);
    User findByUserName(String userName);
}
