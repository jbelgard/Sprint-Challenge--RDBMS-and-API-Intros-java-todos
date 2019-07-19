package com.lambdaschool.todo.controller;

import com.lambdaschool.todo.model.Todo;
import com.lambdaschool.todo.model.User;
import com.lambdaschool.todo.service.TodoService;
import com.lambdaschool.todo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
public class TodoController {
    @Autowired
    private TodoService todoService;

    @Autowired
    private UserService userService;



    @GetMapping(value = "/users/mine", produces = {"application/json"})
    public ResponseEntity<?> getMyTodos(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User currentUser = userService.findUserByUsername(((org.springframework.security.core.userdetails.User)authentication.getPrincipal()).getUsername());
        return new ResponseEntity<>(todoService.findAllById(currentUser.getUserid()), HttpStatus.OK);
    }

    @PostMapping(value = "/users/todo/{userid}", produces = {"application/json"}, consumes = {"application/json"})
    public ResponseEntity<?> postNewTodo(@PathVariable long userid, @RequestBody Todo todo){
        todo.setUser(userService.findUserById(userid));
        return new ResponseEntity<>(todoService.save(todo), HttpStatus.OK);
    }

    @PutMapping(value = "/todos/todoid/{todoid}", produces = {"application/json"}, consumes = {"application/json"})
    public ResponseEntity<?> updateTodo(@PathVariable long todoid, @RequestBody Todo todo){
        return new ResponseEntity<>(todoService.update(todo, todoid), HttpStatus.OK);
    }


}
