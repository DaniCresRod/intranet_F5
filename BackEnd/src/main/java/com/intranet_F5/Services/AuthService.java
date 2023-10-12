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

        //Obtenemos el nombre asociado al email que llega
        String incomingUserName=userRepository.findUserNameByEmail(login.getUsername());

        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        //login.getUsername(),
                        incomingUserName,
                        login.getPassword()
                )
        );
//        UserModel myUser = userRepository.findByUsername(login.getUsername()).orElseThrow();
//        UserDetails user = (UserDetails) userRepository.findByUsername(login.getUsername()).orElseThrow();

        UserModel myUser = userRepository.findByUsername(incomingUserName).orElseThrow();
        UserDetails user = (UserDetails) userRepository.findByUsername(incomingUserName).orElseThrow();

        String myToken = jwtService.getTokenService(user);

        return AuthResponse
                .builder()
                .userId(myUser.getId())
                .token(myToken)
                .build();
    }
}
