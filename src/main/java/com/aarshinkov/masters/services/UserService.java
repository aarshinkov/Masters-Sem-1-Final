package com.aarshinkov.masters.services;

import com.aarshinkov.masters.entities.UserEntity;
import com.aarshinkov.masters.models.users.UserCreateModel;
import com.aarshinkov.masters.models.users.UserEditModel;

import java.util.List;

public interface UserService {
    UserEntity getUserByUserId(Long userId);

    List<UserEntity> getUsers();

    UserEntity createUser(UserCreateModel ucm) throws Exception;

    UserEntity updateUser(UserEditModel uem) throws Exception;

    UserEntity deleteUser(Long userId) throws Exception;
}
