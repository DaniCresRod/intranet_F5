package com.intranet_F5.Controller;

import com.intranet_F5.DTO.request.LoginRequest;
import com.intranet_F5.DTO.response.AuthResponse;
import com.intranet_F5.Services.AuthService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping(value = "login")
    public ResponseEntity<AuthResponse> login(@RequestBody LoginRequest request)
    {
        System.out.println("hola");
        return ResponseEntity.ok(authService.login(request));
    }
//
//    @PostMapping(value = "register")
//    public ResponseEntity<AuthResponse> register(@RequestBody RegisterRequest request)
//    {
//        return ResponseEntity.ok(authService.register(request));
//    }
}