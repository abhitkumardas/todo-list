package com.adtech.todolist.service;

import com.adtech.todolist.model.User;

import java.util.List;

public interface UserService  {
    public List<User> getAllUsers();
    public User getByUserId(Long userId);
    public User getByGuid(String guid);
    public User saveOrFetch(User user);
    public String getGuid(User user);

}
