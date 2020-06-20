package com.adtech.todolist.webservices;

import com.adtech.todolist.model.User;
import com.adtech.todolist.repository.UserRepository;
import com.adtech.todolist.service.ResponseMesseageSerivce;
import com.adtech.todolist.service.UserServiceImpl;
import com.adtech.todolist.util.CustomErrorType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
public class UserResource {

    @Autowired
    UserRepository userRepository;

    @Autowired
    UserServiceImpl userService;

    @Autowired
    ResponseMesseageSerivce responseMesseageSerivce;


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

    @RequestMapping(value = "/deleteUser/{userid}", method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteUser(@PathVariable("userid") String userId) {
        User user = userRepository.findByUserId(Long.parseLong(userId));
        if (user != null) {
            userRepository.deleteById(Long.parseLong(userId));
            return ResponseEntity.ok().body("User Deleted Successfully");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User Not Found :" + userId);
        }
    }

    @RequestMapping(value = "/getUser/{userId}", method = RequestMethod.GET)
    public ResponseEntity user(@PathVariable("userId") String userId) {
        User user = userRepository.findByUserId(Long.parseLong(userId));

        if (user == null) {
            return new ResponseEntity<>(new CustomErrorType("User with id " + userId + " not found"), HttpStatus.NOT_FOUND);
        } else {
            return ResponseEntity.ok(user);
        }
    }

    @RequestMapping(value = "/createUser", method = RequestMethod.POST)
    public ResponseEntity createUser(@RequestBody User user) {

        User result = userService.saveOrFetch(user);
        if (result != null) {
            return ResponseEntity
                    .status(HttpStatus.CONFLICT)
                    .body(responseMesseageSerivce
                            .generateErrorMesseage(null, "User Already Existes", HttpStatus.CONFLICT));
        } else {
            return ResponseEntity.ok(responseMesseageSerivce
                    .generateResponseMessage(null, "User Created Successfully", HttpStatus.OK));
        }
    }

/*    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ResponseEntity login(@RequestBody UserCredentials credentials) {
        *//*String userNameFromRequest = credentials.getUser();
        String passwordRequest = credentials.getPass();
        if ((userNameFromRequest.equals("fero@gmail.com")) && (passwordRequest.equals("pass"))) {
            return ResponseEntity.ok().body("Valid");
        } else {
            return ResponseEntity.ok().body("In Valid");
        }*//*


    }*/


}
