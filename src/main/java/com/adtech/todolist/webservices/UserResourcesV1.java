package com.adtech.todolist.webservices;

import com.adtech.todolist.model.Request.UserRegisterRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("user")
public class UserResourcesV1 {


    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public ResponseEntity register(@RequestHeader("Authorization") String auth, @RequestBody UserRegisterRequest userRegisterRequest, HttpServletRequest servletRequest) {
        System.out.println("Todo");

        return ResponseEntity.ok().body("abcd");
    }
}
