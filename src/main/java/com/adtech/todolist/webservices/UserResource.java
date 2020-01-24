package com.adtech.todolist.webservices;

import com.adtech.todolist.model.User;
import com.adtech.todolist.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserResource {

    @Autowired
    UserRepository userRepository;



    @RequestMapping(value = "/getallusers", method = RequestMethod.GET)
    public List<User> printAllRecords() {
        return userRepository.findAll();
    }

    @RequestMapping(value = "/deleteUser/{userid}", method = RequestMethod.DELETE)
    public ResponseEntity deleteUser(@PathVariable("userid") String userId) {
        userRepository.deleteById(Long.parseLong(userId));
        return ResponseEntity.ok().body("User Deleted Successfully");

    }

    @RequestMapping(value = "/createUser", method = RequestMethod.POST)
    public User createUser(@RequestBody User user) {
        return userRepository.save(user);
    }


}
