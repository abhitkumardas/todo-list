package com.adtech.todolist.service;

import com.adtech.todolist.model.User;
import com.adtech.todolist.repository.UserRepository;
import com.adtech.todolist.util.TodoGuid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    TodoGuid todoGuid;

/*    @Autowired
    BCryptPasswordEncoder passwordEncoder;*/

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }


    @Override
    public User getByUserId(Long userId) {
        return userRepository.findByUserId(userId);
    }

    @Override
    public User getByGuid(String guid) {
        return userRepository.findByGuid(guid);
    }

    @Override
    public User saveOrFetch(User user) {
        user.setGuid(getGuid(user));
        user.setActive(true);
        user.setRoles("USER");
        user.setGrands("READ");
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String hashedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(hashedPassword);
        User user1 = getByGuid(user.getGuid());

        if (user1 == null) {
            userRepository.save(user);
        }
        return user1;
    }

    @Override
    public String getGuid(User user) {
        StringBuilder builder = new StringBuilder(user.getEmail());
//        builder.append(user.getUserName());
        return todoGuid.getUniqueHashId(builder.toString().toLowerCase());
    }

    @Override
    public User getByEmail(String email) {
        return userRepository.findByEmail(email);
    }


}
