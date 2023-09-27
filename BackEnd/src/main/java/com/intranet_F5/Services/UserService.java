package com.intranet_F5.Services;

import com.intranet_F5.Model.UserModel;
import com.intranet_F5.Repository.UserRepository;
import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

//    @Autowired
//    EntityManager entityManager;
    @Autowired
    UserRepository userRepository;

    public List<UserModel> getAllUsers() {
        return userRepository.findAll();
    }

    public String addNewUser(UserModel newUser) {
        try{
            if ( userRepository.findByUserNif(newUser.getUserNif()) == null) {
                userRepository.save(newUser);
                return "El usuario "+newUser.getUserName()+" "+newUser.getUserSurName()+
                        " se guardó satisfactoriamente.";
            }
            else return "Ya existe un usuario con NIF "+newUser.getUserNif();
        }
        catch(Exception e){
            return "Hubo un error al procesar la solicitud de creacion";
        }
    }

    public UserModel getUserById(long id) {
        return userRepository.findById(id).orElse(null);
    }

    public String deleteUser(long id) {
        try{
            if(userRepository.existsById(id)){
                userRepository.deleteById(id);
                return "El usuario ha sido borrado";
            }
            else return "No se encuentra el usuario";

        }catch(Exception e){
            return "Hubo un error al procesar la solicitud de borrado";
        }
    }

    public UserModel upgradeUser(long id, UserModel newUser) {
        try{
            if(userRepository.existsById(id)){
                UserModel myUser=userRepository.findByUserNif(newUser.getUserNif());
                if(myUser.getId() == newUser.getId()){
                    myUser=userRepository.findById(id).get();

                    myUser.setUserName(newUser.getUserName());
                    myUser.setUserSurName(newUser.getUserSurName());
                    myUser.setUserNif(newUser.getUserNif());
                    myUser.setUserEmail(newUser.getUserEmail());
                    myUser.setUserPhone(newUser.getUserPhone());
                    myUser.setUserBirthDate(newUser.getUserBirthDate());
                    myUser.setUserStartDate(newUser.getUserStartDate());
                    myUser.setUserEndDate(newUser.getUserEndDate());
                    //myUser.setUserDays(newUser.getUserDays());
                    myUser.setUserPass(newUser.getUserPass());
                    myUser.setUserType(newUser.getUserType());
                    myUser.setUserImage(newUser.getUserImage());
                    myUser.setSchoolID(newUser.getSchoolID());


                    userRepository.save(myUser);
                    //entityManager.refresh(myUser);
                    return myUser;
                }
                return null;
            }
            else return null;
        }
        catch (Exception e){
            return null;
        }
    }
}