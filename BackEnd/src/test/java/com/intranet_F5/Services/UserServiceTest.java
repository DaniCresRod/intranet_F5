package com.intranet_F5.Services;

import com.intranet_F5.Model.SchoolModel;
import com.intranet_F5.Model.UserModel;
import com.intranet_F5.Model.UserRequestModel;
import com.intranet_F5.Repository.SchoolRepository;
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
import java.util.Optional;
import static java.time.temporal.ChronoUnit.DAYS;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @InjectMocks
    UserService userService;

    @Mock
    UserRepository userRepository;

    @Mock
    SchoolRepository schoolRepository;

    private UserModel testUser1, testUser2;
    private SchoolModel schoolTest1;
    private UserRequestModel requestTest1;

    @BeforeEach
    public void setUp() {
        userService.passwordEncoder = new BCryptPasswordEncoder();

        schoolTest1=new SchoolModel(1L, "School1", "email1@school.com", "PhoneSchool1", null, null, null, SchoolModel.StateCode.AS, null);
        testUser1 = new UserModel(1L, "testUser1", "testSurname1", "testNif1", "test1@example.com",
                "123456789", LocalDate.of(1990, 1, 1), LocalDate.now(), LocalDate.now().plusDays(30),
                30, "password", UserModel.UserType.Formador, null, null, schoolTest1, null);
        testUser2 = new UserModel(2L, "testUser2", "testSurname2", "testNif2", "test2@example.com",
                "987654321", LocalDate.of(1995, 5, 5), LocalDate.now(), LocalDate.now().plusDays(30),
                30, "password", UserModel.UserType.Formador, null, null, null, null);
        requestTest1= new UserRequestModel(1L, testUser1,LocalDate.now(), LocalDate.parse("2023-04-02"), LocalDate.parse("2023-04-10"), 1, UserRequestModel.RequestType.Holidays, null);
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
        //Prueba cuando no hay un usuario ya registrado con ese NIF
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
    void testDeleteUser() {
        //Prueba cuando existe el usuario en la bd
        when(userRepository.existsById(testUser1.getId())).thenReturn(true);

        String response = userService.deleteUser(testUser1.getId());
        assertEquals("El usuario ha sido borrado", response);

        verify(userRepository, times(1)).existsById(testUser1.getId());
        verify(userRepository, times(1)).deleteById(testUser1.getId());

        // Prueba cuando el usuario no existe en la base de datos
        when(userRepository.existsById(testUser1.getId())).thenReturn(false);
        String response2 = userService.deleteUser(testUser1.getId());
        assertEquals("No se encuentra el usuario", response2);
    }

    @Test
    void testUpgradeUser() {
        // Crear un usuario nuevo para actualizar
        SchoolModel schoolTest = new SchoolModel(1L, "Example School", "123 Example St, City", "555-1234",
                null, null, null, SchoolModel.StateCode.AN, null);

        UserModel newUser = new UserModel(1L, "updatedUser", "updatedSurname", "updatedNif", "updated@example.com",
                "987654321", LocalDate.of(1980, 5, 5), LocalDate.now(), LocalDate.now().plusDays(55),
                30, "updatedPassword", UserModel.UserType.Supervisor, null, null, schoolTest, null);

        //Prueba cuando existe el usuario en la bd
        when(userRepository.existsById(1L)).thenReturn(true);

        //Prueba cuando SI existe el usuario en la base de datos
        when(userRepository.findByUserNif(newUser.getUserNif())).thenReturn(testUser1);
        when(userRepository.findById(testUser1.getId())).thenReturn(Optional.of(testUser1));

        when(schoolRepository.findById(testUser1.getSchoolID().getId())).thenReturn(Optional.of(schoolTest));

        UserModel updatedUser = userService.upgradeUser(1L, newUser);

        assertEquals(newUser.getUsername(), updatedUser.getUsername());
        assertEquals(newUser.getUserSurName(), updatedUser.getUserSurName());
        assertEquals(newUser.getUserNif(), updatedUser.getUserNif());
        assertEquals(newUser.getUserEmail(), updatedUser.getUserEmail());

        verify(userRepository, times(1)).findById(1L);
        verify(userRepository, times(1)).save(testUser1);
    }

    @Test
    void testHldyDaysLeft() {

        // Simular la respuesta del repositorio al buscar el usuario por ID
        when(userRepository.findById(1L)).thenReturn(Optional.of(testUser1));

        // Crear una solicitud de usuario de prueba
        LocalDate startDate = LocalDate.now();
        LocalDate endDate = startDate.plusDays(5); // Cambiar el número de días según tus necesidades
        UserRequestModel myRequest = requestTest1;

        // Llamar al método que queremos probar
        int result = userService.HldyDaysLeft(1L, myRequest);

        // Asegurarse de que el método haya funcionado según lo esperado
        long differenceDays = DAYS.between(startDate, endDate);
        if (differenceDays > testUser1.getUserDays()) {
            assertEquals(-1, result);
        } else {
            if (myRequest.getStatus() == 2) {
                int expectedDays = (int) (testUser1.getUserDays() - differenceDays);
                assertEquals(expectedDays, result);
                verify(userRepository, times(1)).findById(1L);
                verify(userService, times(1)).upgradeUser(1L, testUser1);
            }
        }
    }
}