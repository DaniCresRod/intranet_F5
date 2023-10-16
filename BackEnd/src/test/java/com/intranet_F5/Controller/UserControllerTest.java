package com.intranet_F5.Controller;

import com.intranet_F5.Model.UserModel;
import com.intranet_F5.Repository.UserRepository;
import com.intranet_F5.Services.JwtService;
import com.intranet_F5.Services.LogService;
import com.intranet_F5.Services.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.TestingAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.security.web.csrf.CsrfTokenRepository;
import org.springframework.security.web.csrf.DefaultCsrfToken;
import org.springframework.security.web.csrf.HttpSessionCsrfTokenRepository;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;

//@SpringBootTest
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
    //@WithMockUser(username = "root", roles = "HR")
    void addNewUser() throws Exception {


        String userJson = "{\"userName\": \"John\", \"userSurName\": \"Doe\", \"userNif\": \"12345\", \"userEmail\": \"john@example.com\", \"userPhone\": \"123456789\", \"userType\": \"Formador\", \"userDept\": \"Pedagógico\", \"userPass\": \"password\"}";

        when(userService.addNewUser(any(UserModel.class))).thenReturn("User added successfully");

        MockHttpServletResponse response = mockMvc.perform(MockMvcRequestBuilders.post("/users").with(csrf().asHeader())

                        .contentType(MediaType.APPLICATION_JSON)
                        .header(HttpHeaders.AUTHORIZATION, "Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJPcHRpbXVzIiwiaWF0IjoxNjk3NDUxMjk5LCJleHAiOjE2OTc1Mzc2OTl9.UUQ5mWU1UiYCcncLBMEJRYnLJbxxByz96Rd4_chIbts"))
                .andReturn()
                .getResponse();

        assertEquals(200, response.getStatus());
        assertEquals("User added successfully", response.getContentAsString());
    }
}