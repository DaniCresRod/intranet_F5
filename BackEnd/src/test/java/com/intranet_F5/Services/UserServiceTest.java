package com.intranet_F5.Services;

import com.intranet_F5.Model.UserModel;
import com.intranet_F5.Repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @InjectMocks
    UserService userService;

    @Mock
    UserRepository userRepository;

    private UserModel testUser1, testUser2;
    @BeforeEach
    public void setUp() {
        userService.passwordEncoder = new BCryptPasswordEncoder();

        testUser1 = new UserModel(1L, "testUser1", "testSurname1", "testNif1", "test1@example.com",
                "123456789", LocalDate.of(1990, 1, 1), LocalDate.now(), LocalDate.now().plusDays(30),
                30, "password", UserModel.UserType.Formador, null, null, null, null);
        testUser2 = new UserModel(2L, "testUser2", "testSurname2", "testNif2", "test2@example.com",
                "987654321", LocalDate.of(1995, 5, 5), LocalDate.now(), LocalDate.now().plusDays(30),
                30, "password", UserModel.UserType.Formador, null, null, null, null);
    }

    @Test
    void testGetAllUsers() {
        List<UserModel> userList = new ArrayList<>();
        userList.add(testUser1);
        userList.add(testUser2);

        when(userRepository.findAll()).thenReturn(userList);

        List<UserModel> result = userService.getAllUsers();

        assertEquals(2, result.size());
        assertEquals("testUser1", result.get(0).getUsername());
        assertEquals("testUser2", result.get(1).getUsername());
    }

    @Test
    void testAddNewUser() {

        when(userRepository.findByUserNif(testUser1.getUserNif())).thenReturn(null);
        when(userRepository.save(testUser1)).thenReturn(testUser1);

        String resultSuccess = userService.addNewUser(testUser1);
        assertEquals("El usuario testUser1 testSurname1 se guardó satisfactoriamente.", resultSuccess);

        // Prueba para verificar que se llama al método findByUserNif
        verify(userRepository, times(1)).findByUserNif(testUser1.getUserNif());

        // Prueba para verificar que se llama al método save
        verify(userRepository, times(1)).save(testUser1);

        // Prueba cuando ya existe un usuario con el mismo NIF
        when(userRepository.findByUserNif(testUser1.getUserNif())).thenReturn(testUser1);

        String resultFailure = userService.addNewUser(testUser1);
        assertEquals("Ya existe un usuario con NIF " + testUser1.getUserNif(), resultFailure);
    }

    @Test
    void getUserById() {
    }

    @Test
    void deleteUser() {
    }

    @Test
    void upgradeUser() {
    }

    @Test
    void hldyDaysLeft() {
    }
}