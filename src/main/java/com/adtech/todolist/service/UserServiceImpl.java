package com.adtech.todolist.service;

import com.adtech.todolist.model.User;
import com.adtech.todolist.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;


    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User getByUserId(String userId) {
        return userRepository.findByUserId(userId);
    }




}
