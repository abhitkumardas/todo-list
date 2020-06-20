package com.adtech.todolist.webservices;


import com.adtech.todolist.codetype.TodoStatus;
import com.adtech.todolist.model.Todo;
import com.adtech.todolist.model.User;
import com.adtech.todolist.model.request.TodoReq;
import com.adtech.todolist.repository.TodoRepository;
import com.adtech.todolist.repository.UserRepository;
import com.adtech.todolist.service.ResponseMesseageSerivce;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
public class TodoResource {
    @Autowired
    TodoRepository todoRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    ResponseMesseageSerivce responseMesseageSerivce;

    @RequestMapping(value = "/createTodo", method = RequestMethod.POST)
    public void createTodo(@RequestBody TodoReq todoReq) {
        String userId = todoReq.getUserId();
        User user = userRepository.findByUserId(Long.valueOf(userId));
        Todo todo = new Todo();
        todo.setUser(user);
        todo.setTodo(todoReq.getTodo());
        todo.setStatus(TodoStatus.ACTIVE);
        todoRepository.save(todo);
    }

    @RequestMapping(value = "/updateTodoStatus/{todoId}", method = RequestMethod.PUT)
    public void updateStatus(@PathVariable("todoId") String todoId) {
        Todo todo = todoRepository.findByTodoId(Long.parseLong(todoId));
        todo.setTodo(todo.getTodo());
        todo.setTodoId(todo.getTodoId());
        todo.setStatus(TodoStatus.COMPLETED);
        todoRepository.save(todo);
    }

    @RequestMapping(value = "/deleteTodo/{todoId}", method = RequestMethod.DELETE)
    public void deleteTodo(@PathVariable("todoId") String todoId) {
        Todo todo = todoRepository.findByTodoId(Long.parseLong(todoId));
        todoRepository.delete(todo);
    }

    @RequestMapping(value = "/getAllTodo/{userId}", method = RequestMethod.GET)
    public ResponseEntity allTodos(@PathVariable("userId") String userId) {
        User user = userRepository.findByUserId(Long.parseLong(userId));
        if (user != null)
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(responseMesseageSerivce.generateResponseMessage(todoRepository.findByUser(user), "", HttpStatus.OK));
        else {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(responseMesseageSerivce.generateErrorMesseage(null, "User Not Found", HttpStatus.NOT_FOUND));
        }
    }

}
