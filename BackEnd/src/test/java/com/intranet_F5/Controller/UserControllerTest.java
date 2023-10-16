package com.intranet_F5.Controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.intranet_F5.Model.UserModel;
import com.intranet_F5.Repository.UserRepository;
import com.intranet_F5.Services.JwtService;
import com.intranet_F5.Services.LogService;
import com.intranet_F5.Services.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;

@WebMvcTest(UserController.class)
@AutoConfigureMockMvc
class UserControllerTest {
    @Autowired
    MockMvc mockMvc;
    @MockBean
    UserService userService;

    @MockBean
    JwtService jwtService;

    @MockBean
    LogService logService;

    @MockBean
    UserRepository UserRepository;

    @MockBean
    UserDetailsService userDetailsService;

    @Test
    @WithMockUser(username = "root", roles = "HR")
    void addNewUser() throws Exception {


        String userJson = "{\"userName\": \"John\", \"userSurName\": \"Doe\", \"userNif\": \"12345\", \"userEmail\": \"john@example.com\", \"userPhone\": \"123456789\", \"userType\": \"Formador\", \"userDept\": \"Pedagógico\", \"userPass\": \"password\"}";
        String myToken="Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJPcHRpbXVzIiwiaWF0IjoxNjk3NDUxMjk5LCJleHAiOjE2OTc1Mzc2OTl9.UUQ5mWU1UiYCcncLBMEJRYnLJbxxByz96Rd4_chIbts";


        when(userService.addNewUser(any(UserModel.class))).thenReturn("User added successfully");

        MockHttpServletResponse response = mockMvc.perform(MockMvcRequestBuilders
                .post("/users").with(csrf().asHeader())
                .content(userJson)
                .contentType(MediaType.APPLICATION_JSON)
                .header(HttpHeaders.AUTHORIZATION, myToken ))

                .andReturn()
                .getResponse();

        assertEquals(200, response.getStatus());
        assertEquals("User added successfully", response.getContentAsString());
    }

    @Test
    @WithMockUser(username = "root", roles = "HR")
    void getAllUsers() throws Exception {
        UserModel myTestUser1= new UserModel(1L, "userName1","userSurname1", "userNif1", "user1@email.com", "userPhone1",null, null, null, 30, "null", UserModel.UserType.Formador, null, null, null, null );
        List<UserModel> users = new ArrayList<>();
        users.add(myTestUser1);

        String myToken="Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJPcHRpbXVzIiwiaWF0IjoxNjk3NDUxMjk5LCJleHAiOjE2OTc1Mzc2OTl9.UUQ5mWU1UiYCcncLBMEJRYnLJbxxByz96Rd4_chIbts";

        when(userService.getAllUsers()).thenReturn(users);

        MockHttpServletResponse response = mockMvc.perform(MockMvcRequestBuilders
                        .get("/users").with(csrf().asHeader())
                        .contentType(MediaType.APPLICATION_JSON)
                        .header(HttpHeaders.AUTHORIZATION, myToken ))

                .andReturn()
                .getResponse();

        assertEquals(200, response.getStatus());

        ObjectMapper objectMapper = new ObjectMapper();
        //Evita problemas con username y password de UserDetails, asi no da error cuando no los encuentra
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        List<UserModel> userResponseList = objectMapper.readValue(response.getContentAsString(),
                new TypeReference<List<UserModel>>() {}
        );
        UserModel userResponse = userResponseList.get(0);

        assertEquals(myTestUser1.getUsername(),  userResponse.getUsername());
    }

}