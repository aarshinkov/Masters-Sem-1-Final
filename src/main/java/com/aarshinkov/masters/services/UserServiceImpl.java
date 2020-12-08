package com.aarshinkov.masters.services;

import com.aarshinkov.masters.entities.RoleEntity;
import com.aarshinkov.masters.entities.UserEntity;
import com.aarshinkov.masters.models.users.UserCreateModel;
import com.aarshinkov.masters.models.users.UserEditModel;
import com.aarshinkov.masters.repositories.UsersRepository;
import com.aarshinkov.masters.security.UserPrincipal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class UserServiceImpl implements UserService, UserDetailsService {

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

        Set<RoleEntity> roles = new HashSet<>();

        for (String role : ucm.getRoles()) {
            roles.add(new RoleEntity(role.toUpperCase()));
        }

        user.setRoles(roles);

        return usersRepository.save(user);
    }

    @Override
    public UserEntity updateUser(UserEditModel uem) throws Exception {
        return null;
    }

    @Override
    public UserEntity deleteUser(Long userId) throws Exception {
        return null;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        UserEntity user = usersRepository.findByEmail(email);

        if (user == null) {
            throw new UsernameNotFoundException(email);
        }

        Set<RoleEntity> roles = user.getRoles();

        return new UserPrincipal(user, roles);
    }
}
