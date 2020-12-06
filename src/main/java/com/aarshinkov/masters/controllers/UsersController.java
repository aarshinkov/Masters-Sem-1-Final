package com.aarshinkov.masters.controllers;

import com.aarshinkov.masters.entities.UserEntity;
import com.aarshinkov.masters.models.users.UserCreateModel;
import com.aarshinkov.masters.models.users.UserEditModel;
import com.aarshinkov.masters.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//@Controller
@RestController
public class UsersController {

    @Autowired
    private UserService userService;

    @GetMapping("/users")
    public List<UserEntity> getUsers() {
        return userService.getUsers();
    }

    @GetMapping("/users/{userId}")
    public UserEntity getUser(@PathVariable("userId") Long userId) {
        return userService.getUserByUserId(userId);
    }

    @PostMapping("/users")
    public UserEntity createUser(@RequestBody UserCreateModel ucm) throws Exception {
        return userService.createUser(ucm);
    }

    @PutMapping("/users")
    public UserEntity updateUser(@RequestBody UserEditModel uem) throws Exception {
        return userService.updateUser(uem);
    }

    @DeleteMapping("/users/{userId}")
    public UserEntity deleteUser(@PathVariable("userId") Long userId) throws Exception {
        return userService.deleteUser(userId);
    }
}
