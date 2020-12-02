package com.aarshinkov.masters.services;

import com.aarshinkov.masters.entities.UserEntity;
import com.aarshinkov.masters.models.users.UserCreateModel;
import com.aarshinkov.masters.models.users.UserEditModel;
import com.aarshinkov.masters.repositories.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UsersRepository usersRepository;

    @Override
    public List<UserEntity> getUsers() {
        return usersRepository.findAll();
    }

    @Override
    public UserEntity getUserByUserId(Long userId) {
        return usersRepository.findByUserId(userId);
    }

    @Override
    public UserEntity createUser(UserCreateModel ucm) throws Exception {
        UserEntity storedUser = usersRepository.findByEmail(ucm.getEmail());

        if (storedUser != null) {
            throw new Exception("User with email " + ucm.getEmail() + " already exists.");
        }

        UserEntity user = new UserEntity();
        user.setEmail(ucm.getEmail());
        user.setPassword(ucm.getPassword());
        user.setFirstName(ucm.getFirstName());
        user.setLastName(ucm.getLastName());

        return usersRepository.save(user);
    }

    @Override
    public UserEntity editUser(UserEditModel uem) throws Exception {
        return null;
    }
}
