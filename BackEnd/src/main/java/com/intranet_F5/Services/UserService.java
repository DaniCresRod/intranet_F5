package com.intranet_F5.Services;

import com.intranet_F5.Model.UserModel;
import com.intranet_F5.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;


    public List<UserModel> getAllUsers() {
        return userRepository.findAll();
    }
}