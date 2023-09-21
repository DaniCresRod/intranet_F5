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

    public String addnewUser(UserModel newUser) {
        try{
            if ( userRepository.findByUserNif(newUser.getUserNif()) == null) {
                userRepository.save(newUser);
                return "El usuario "+newUser.getUserName()+" "+newUser.getUserSurName()+
                        " se guardó satisfactoriamente.";
            }
            else return "Ya existe un usuario con NIF "+newUser.getUserNif();
        }
        catch(Exception e){
            return "Hubo un error al procesar la solicitud";
        }
    }
}