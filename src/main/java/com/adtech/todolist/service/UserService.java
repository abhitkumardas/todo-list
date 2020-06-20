package com.adtech.todolist.service;

import com.adtech.todolist.model.User;

import java.util.List;

public interface UserService {
    List<User> getAllUsers();

    User getByUserId(Long userId);

    User getByGuid(String guid);

    User saveOrFetch(User user);

    String getGuid(User user);

    User getByEmail(String email);

}
