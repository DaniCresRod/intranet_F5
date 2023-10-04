package com.intranet_F5.Services;

import com.intranet_F5.Model.SchoolDateModel;
import com.intranet_F5.Model.SchoolModel;
import com.intranet_F5.Model.UserModel;
import com.intranet_F5.Repository.SchoolDateRepository;
import com.intranet_F5.Repository.SchoolRepository;
import com.intranet_F5.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SchoolService {
    @Autowired
    SchoolRepository schoolRepository;
    @Autowired
    private SchoolDateRepository schoolDateRepository;

    @Autowired
    private UserRepository userRepository;

    public List<SchoolModel> getAllSchools(){
        return schoolRepository.findAll();
    }

    public String createSchool(SchoolModel newSchool) {
        try{
            if (schoolRepository.findDuplicatedSchool(newSchool) == null) {

                //Traer los festivos para completar el log
                AccessBankHolidaysAPI accessBankHolidaysAPI=new AccessBankHolidaysAPI();
                List<SchoolDateModel>schoolDateModels=accessBankHolidaysAPI.getSchoolDateModelList(newSchool);

                schoolDateRepository.saveAll(schoolDateModels);
                newSchool.setSchoolStateHolidays(schoolDateModels);
                schoolRepository.save(newSchool);

                return "La escuela de "+newSchool.getSchoolName()+" se guardó satisfactoriamente";
            } else return "La escuela de "+newSchool.getSchoolName()
                    +" con el email "+newSchool.getSchoolAddress()+" ya existe. No se guardó.";
        }
        catch(Exception e){
            return "Hubo un error al procesar la solicitud de guardado";
        }
    }

    public SchoolModel getOneSchoolById(long id) {
        return schoolRepository.findById(id).orElse(null);
    }

    public String deleteSchool(long id) {
        try{
            if(schoolRepository.existsById(id)){
                schoolRepository.deleteById(id);
            }
            else return "No se encontró la escuela solicitada";

            return "La escuela se borró correctamente";
        }
        catch(Exception e){
            return "Hubo un error al procesar la solicitud de borrado escuela";
        }
    }

    public SchoolModel updateSchool(long id, SchoolModel updatedSchool) {
        try{
            if(schoolRepository.existsById(id)){
                SchoolModel mySchool=schoolRepository.findById(id).get();
                //Si existe OTRA escuela con el mismo nombre y direccion, pero diferente id
                //Querra decir que no es la que queremos modificar, si no otra que ya existe
                //y no debemos dejar que se modifique para no tener dos escuelas con los mismos datos
                //Si lo permitira si solo uno de los dos datos coincide (modificar??)
                SchoolModel duplicatedSchool=schoolRepository.findDuplicatedSchool(updatedSchool);
                if((duplicatedSchool==null)||(duplicatedSchool.getId() == mySchool.getId())){
                    mySchool.setSchoolName(updatedSchool.getSchoolName() != null ? updatedSchool.getSchoolName() : mySchool.getSchoolName());
                    mySchool.setSchoolAddress(updatedSchool.getSchoolAddress() != null ? updatedSchool.getSchoolAddress() : mySchool.getSchoolAddress());
                    mySchool.setSchoolPhone(updatedSchool.getSchoolPhone() != null ? updatedSchool.getSchoolPhone() : mySchool.getSchoolPhone());
                    mySchool.setSchoolBankHs(updatedSchool.getSchoolBankHs() != null ? updatedSchool.getSchoolBankHs() : mySchool.getSchoolBankHs());

                    if(updatedSchool.getSchoolSupervisor() != null){
                        UserModel mySupervisor = userRepository.findById(updatedSchool.getSchoolSupervisor().getId()).get();
                        mySchool.setSchoolSupervisor(mySupervisor);
                    }

                    if((updatedSchool.getSchoolStateCode()!=null) && (mySchool.getSchoolStateCode()!=updatedSchool.getSchoolStateCode())){
                        mySchool.setSchoolStateCode(updatedSchool.getSchoolStateCode());

                        //Traer los festivos para completar el log
                        AccessBankHolidaysAPI accessBankHolidaysAPI=new AccessBankHolidaysAPI();
                        List<SchoolDateModel>schoolDateModels=accessBankHolidaysAPI.getSchoolDateModelList(mySchool);
                        mySchool.setSchoolStateHolidays(schoolDateModels);
                        schoolDateRepository.saveAll(schoolDateModels);
                        mySchool.setSchoolStateHolidays(schoolDateModels);
                    }

                    schoolRepository.save(mySchool);
                    return mySchool;
                }
                else return mySchool;
            }
            return null;
        }
        catch(Exception e){
            return null;
        }
    }
}
