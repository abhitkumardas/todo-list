package com.adtech.todolist.webservices;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestResource {

    @RequestMapping("/")
    public String welcome(){
        return "<h2> Welcome to Our Todo List Application </h2>";
    }

    @RequestMapping(value = "/print/{text}", method = RequestMethod.GET)
    public String print(@PathVariable("text") String text){
        return "<h1>"+ text +"</h1>";
    }

}
