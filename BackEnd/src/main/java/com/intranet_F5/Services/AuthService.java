package com.intranet_F5.Services;

import com.intranet_F5.DTO.request.LoginRequest;
import com.intranet_F5.DTO.response.AuthResponse;
import com.intranet_F5.Model.UserModel;
import com.intranet_F5.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final JwtService jwtService;
    private final UserRepository userRepository;
    private final AuthenticationManager authenticationManager;

    public AuthResponse login(LoginRequest login){

        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        login.getUsername(),
                        login.getPassword()
                )
        );

        System.out.println("Paso el authenticatorManager en AuthService");
        UserModel myUser = userRepository.findByUsername(login.getUsername()).orElseThrow();
        UserDetails user = (UserDetails) userRepository.findByUsername(login.getUsername()).orElseThrow();

        System.out.println("Paso el UserDetails en AuthService");

        String myToken = jwtService.getTokenService(user);
        System.out.println("Paso el jwtService en AuthService");

        return AuthResponse
                .builder()
                .userId(myUser.getId())
                .token(myToken)
                .build();
    }

//    public  AuthResponse register(RegisterRequest register){
//        User user = User.builder()
//                .username(register.getUsername())
//                .password(passwordEncoder.encode(register.getPassword()))
//                .firstname(register.getFirstname())
//                .lastname(register.getFirstname())
//                .country(register.getCountry())
//                .role(ERole.USER)
//                .build();
//
//        userRepository.save(user);
//
//        return AuthResponse
//                .builder()
//                .token(jwtService.getTokenService(user))
//                .build();
//    }
}
