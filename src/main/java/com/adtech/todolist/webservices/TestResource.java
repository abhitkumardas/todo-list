package com.adtech.todolist.webservices;

import com.adtech.todolist.model.User;
import com.adtech.todolist.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class TestResource {

    @Autowired
    UserRepository userRepository;

    @RequestMapping("/")
    public String welcome() {
        return "<h2> Welcome to Our Todo List Application </h2>";
    }

    @RequestMapping(value = "/print/{text}", method = RequestMethod.GET)
    public String print(@PathVariable("text") String text) {
        return "<h1>" + text + "</h1>";
    }

    @RequestMapping(value = "/getallusers", method = RequestMethod.GET)
    public List<User> printAllRecords() {
        return userRepository.findAll();
    }

    @RequestMapping(value = "/createUser", method = RequestMethod.POST)
    public User createUser() {
        User user = new User();
        user.setUserName("fero");
        user.setGuid("123");
        user.setEmail("fero@Gmail.com");
        return userRepository.save(user);
    }


}
