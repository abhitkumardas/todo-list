package com.adtech.todolist.webservices;

import com.adtech.todolist.model.Request.UserRegisterRequest;
import com.adtech.todolist.model.User;
import com.adtech.todolist.service.ResponseMessageService;
import com.adtech.todolist.utils.TodoUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.security.AuthProvider;

@RestController
@RequestMapping("user")
public class UserResourceV1 {

    @Autowired
    private TodoUtils todoUtils;

    @Autowired
    private AuthProvider authService;

    @Autowired
    private ResponseMessageService responseMessageService;


    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public ResponseEntity register(@RequestHeader("Authorization") String authHeader, @RequestBody UserRegisterRequest userRegisterRequest, HttpServletRequest servletRequest) {
        System.out.println("Todo");
        return ResponseEntity.ok().body("abcd");
    }

    @RequestMapping(value="/login", method = RequestMethod.POST)
    public ResponseEntity register(@RequestHeader("Authorization") String authHeader, @RequestBody User user){

        if (todoUtils.stringNotEmpty(authHeader)){

            //authService.fetchAccessToken(authHeader, user.getEmail(), user.getPassword());
            return ResponseEntity
                    .ok(responseMessageService
                            .generateResponseMessage(
                                    null,"login.success", HttpStatus.OK));
        }else {
            return ResponseEntity
                    .status(HttpStatus.FORBIDDEN)
                    .body(responseMessageService
                            .generateResponseMessage(null,"unauthorised.request", HttpStatus.FORBIDDEN));
        }
    }
}
