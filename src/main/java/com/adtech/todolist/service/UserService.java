package com.adtech.todolist.service;

import com.adtech.todolist.model.User;

import java.util.List;

public interface UserService  {
    public List<User> getAllUsers();
    public User getByUserId(String userId);
    public User create(User user);


}
