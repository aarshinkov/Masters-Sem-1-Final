package com.aarshinkov.masters.services;

import com.aarshinkov.masters.entities.UserEntity;
import com.aarshinkov.masters.models.users.UserCreateModel;
import com.aarshinkov.masters.repositories.UsersRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceTest {
    @Mock
    private UsersRepository usersRepository;

    @InjectMocks
    private final UserService userService = new UserServiceImpl();

    @Test
    public void getUsers_ReturnNull_IfDataPresent() {
        List<UserEntity> usersList = new ArrayList<>();
        usersList.add(new UserEntity());
        usersList.add(new UserEntity());

        given(usersRepository.findAll()).willReturn(usersList);

        List<UserEntity> users = userService.getUsers();

        assertNotNull(users);
        assertEquals(2, users.size());

        verify(usersRepository, times(1)).findAll();
    }

    @Test
    public void getUsers_ReturnEmptyList_IfNoData() {
        given(usersRepository.findAll()).willReturn(new ArrayList<>());

        List<UserEntity> users = userService.getUsers();

        assertNotNull(users);
        assertEquals(0, users.size());

        verify(usersRepository, times(1)).findAll();
    }

    @Test
    public void getUserByUserId_ReturnNull_IfIdInvalid() {

        given(usersRepository.findByUserId(anyLong())).willReturn(null);

        UserEntity storedUser = userService.getUserByUserId(anyLong());

        assertNull(storedUser);

        verify(usersRepository, times(1)).findByUserId(anyLong());
    }

    @Test
    public void getUserByUserId_ReturnEntity_IfIdValid() {

        UserEntity user = new UserEntity(1L, "test@gmail.com", "Test-1234", "Atanas", "Arshinkov");

        given(usersRepository.findByUserId(anyLong())).willReturn(user);

        UserEntity storedUser = userService.getUserByUserId(anyLong());

        assertNotNull(storedUser);

        verify(usersRepository, times(1)).findByUserId(anyLong());
    }

    @Test
    public void createUser_ThrowException_IfMailExists() {
        UserEntity storedUser = new UserEntity();
        storedUser.setEmail("test@gmail.com");

        UserCreateModel ucm = new UserCreateModel();
        ucm.setEmail("test@gmail.com");

        given(usersRepository.findByEmail(anyString())).willReturn(storedUser);
        assertThrows(Exception.class, () -> userService.createUser(ucm));

        verify(usersRepository, times(1)).findByEmail(anyString());
        verify(usersRepository, never()).save(any());
    }

    @Test
    public void createUser_saveOnlyOneTime_IfDataValid() throws Exception {
        UserEntity createdUser = new UserEntity();
        createdUser.setUserId(1L);
        createdUser.setEmail("test@gmail.com");
        createdUser.setPassword("Test-1234");
        createdUser.setFirstName("Atanas");
        createdUser.setLastName("Arshinkov");

        given(usersRepository.findByEmail(anyString())).willReturn(null);
        given(usersRepository.save(any())).willReturn(createdUser);

        UserCreateModel ucm = new UserCreateModel();
        ucm.setEmail("test@gmail.com");
        ucm.setPassword("Test-1234");
        ucm.setFirstName("Atanas");
        ucm.setLastName("Arshinkov");
        UserEntity result = userService.createUser(ucm);
        assertNotNull(result);

        verify(usersRepository, times(1)).save(any());
    }
}
