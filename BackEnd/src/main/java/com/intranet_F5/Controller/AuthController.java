package com.intranet_F5.Controller;

import com.intranet_F5.DTO.request.LoginRequest;
import com.intranet_F5.DTO.response.AuthResponse;
import com.intranet_F5.Services.AuthService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class AuthController {

    private final AuthService authService;

    @PostMapping(value = "/login")
    public ResponseEntity<AuthResponse> login(@RequestBody LoginRequest request)
    {
        try{
            System.out.println("Entra en /login en AuthController");
            return ResponseEntity.ok(authService.login(request));
        }
        catch(Exception e){
            System.out.println("Excepcion en AuthController");
            System.out.println(e.getMessage());
            return null;
        }
    }
}