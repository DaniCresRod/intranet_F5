package com.intranet_F5.Services;

import com.intranet_F5.Model.SchoolModel;
import com.intranet_F5.Model.UserModel;
import com.intranet_F5.Model.UserRequestModel;
import com.intranet_F5.Repository.SchoolRepository;
import com.intranet_F5.Repository.UserRepository;
import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

import static java.time.temporal.ChronoUnit.DAYS;
import static java.time.temporal.ChronoUnit.MONTHS;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    SchoolRepository schoolRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    public List<UserModel> getAllUsers() {
        return userRepository.findAll();
    }

    public String addNewUser(UserModel newUser) {
        try{
            if ( userRepository.findByUserNif(newUser.getUserNif()) == null) {
                //Encriptar contraseña
                String encodedPassword = passwordEncoder.encode(newUser.getUserPass());
                newUser.setUserPass(encodedPassword);

                userRepository.save(newUser);
                return "El usuario "+newUser.getUsername()+" "
                        +newUser.getUserSurName()+" se guardó satisfactoriamente.";
            }
            else return "Ya existe un usuario con NIF "+newUser.getUserNif();
        }
        catch(Exception e){
            return "Hubo un error al procesar la solicitud de creacion: "+e.getMessage();
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

                    if(newUser.getUsername()!=null)myUser.setUserName(newUser.getUsername());
                    if(newUser.getUserSurName()!=null) myUser.setUserSurName(newUser.getUserSurName());
                    if(newUser.getUserNif()!=null)myUser.setUserNif(newUser.getUserNif());
                    if(newUser.getUserEmail()!=null)myUser.setUserEmail(newUser.getUserEmail());
                    if(newUser.getUserPhone()!=null)myUser.setUserPhone(newUser.getUserPhone());
                    if(newUser.getUserBirthDate()!=null)myUser.setUserBirthDate(newUser.getUserBirthDate());
                    if(newUser.getUserStartDate()!=null)myUser.setUserStartDate(newUser.getUserStartDate());
                    myUser.setUserEndDate(newUser.getUserEndDate());
                    if(newUser.getUserDays()!=null)myUser.setUserDays(newUser.getUserDays());
                    if(newUser.getUserType()!=null)myUser.setUserType(newUser.getUserType());
                    if(newUser.getUserDept()!=null)myUser.setUserDept(newUser.getUserDept());
                    if(newUser.getUserImage()!=null)myUser.setUserImage(newUser.getUserImage());
                    if(newUser.getSchoolID()!=null) myUser.setSchoolID(newUser.getSchoolID());
                    if(newUser.getUserDept()!=null)myUser.setUserDept(newUser.getUserDept());
                    if((newUser.getUserPass()!=null)&&(newUser.getUserPass()!="")){
                        String encodedPassword = passwordEncoder.encode(newUser.getUserPass());
                        myUser.setUserPass(encodedPassword);
                    }

                    //Actualizar la info de la escuela para enviar, para saltarme el cache de Hibernate
                    //No funcionó con entityManager.refresh(myUser);
                    SchoolModel updatedSchool=schoolRepository.findById(myUser.getSchoolID().getId()).get();

                    myUser.getSchoolID().setSchoolName(updatedSchool.getSchoolName());
                    myUser.getSchoolID().setSchoolAddress(updatedSchool.getSchoolAddress());
                    myUser.getSchoolID().setSchoolPhone(updatedSchool.getSchoolPhone());
                    myUser.getSchoolID().setSchoolBankHs(updatedSchool.getSchoolBankHs());
                    myUser.getSchoolID().setSchoolStateHolidays(updatedSchool.getSchoolStateHolidays());
                    myUser.getSchoolID().setSchoolStateCode(updatedSchool.getSchoolStateCode());

                    userRepository.save(myUser);
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

    public int HldyDaysLeft(long userId, UserRequestModel myRequest){
        UserModel myUser=userRepository.findById(userId).get();
        long differenceDays=DAYS.between(myRequest.getStartDate(), myRequest.getEndDate());
        if(differenceDays>myUser.getUserDays()) return -1;
        else {
            if(myRequest.getStatus()==2){
                myUser.setUserDays((int)(myUser.getUserDays()-differenceDays));
                this.upgradeUser(myUser.getId(), myUser);
            }
            return (int)(myUser.getUserDays()-differenceDays);
        }
    }
}