package com.aarshinkov.masters.controllers;

import com.aarshinkov.masters.entities.UserEntity;
import com.aarshinkov.masters.models.users.UserCreateModel;
import com.aarshinkov.masters.models.users.UserEditModel;
import com.aarshinkov.masters.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
public class UsersController {

    @Autowired
    private UserService userService;

    @GetMapping("/users/{userId}")
    public UserEntity getUserByUserId(@PathVariable("userId") Long userId) {
        return userService.getUserByUserId(userId);
    }

    @GetMapping("/users")
    public List<UserEntity> getUsers() {
        return userService.getUsers();
    }

    @PostMapping("/users/create")
    public UserEntity createUser(@Valid @RequestBody UserCreateModel ucm) throws Exception {
        return userService.createUser(ucm);
    }

    @PostMapping("/users/edit")
    public UserEntity editUser(@RequestBody UserEditModel uem) throws Exception {
        return userService.editUser(uem);
    }
}
