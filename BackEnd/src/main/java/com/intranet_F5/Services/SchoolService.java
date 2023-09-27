package com.intranet_F5.Services;

import com.intranet_F5.Model.SchoolDateModel;
import com.intranet_F5.Model.SchoolModel;
import com.intranet_F5.Repository.SchoolDateRepository;
import com.intranet_F5.Repository.SchoolRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class SchoolService {
    @Autowired
    SchoolRepository schoolRepository;

    @Autowired
    private SchoolDateRepository schoolDateRepository;

    public List<SchoolModel> getAllSchools(){
        return schoolRepository.findAll();
    }

    public String createSchool(SchoolModel newSchool) {
        try{
            if (schoolRepository.findDuplicatedSchool(newSchool) == null) {

                //Traer los festivos para completar el log
                AccessBankHolidaysAPI accessBankHolidaysAPI=new AccessBankHolidaysAPI();
                List<LocalDate> holidays = accessBankHolidaysAPI.fetchHolidays(newSchool.getSchoolStateCode());

                List<SchoolDateModel> schoolDateModels = new ArrayList<>();
                for (LocalDate date : holidays) {
                    SchoolDateModel schoolDateModel = new SchoolDateModel();
                    schoolDateModel.setDate(date);
                    schoolDateModels.add(schoolDateModel);
                }

                schoolDateRepository.saveAll(schoolDateModels);

                newSchool.setSchoolStateHolidays(schoolDateModels);

                schoolRepository.save(newSchool);
                return "La escuela de "+newSchool.getSchoolName()+" se guardó satisfactoriamente";
            } else return "La escuela de "+newSchool.getSchoolName()
                    +" con el email "+newSchool.getSchoolEmail()+" ya existe. No se guardó.";
        }
        catch(Exception e){
            return "Hubo un error al procesar la solicitud";
        }
    }

    public SchoolModel getOneSchoolById(long id) {
        return schoolRepository.findById(id).orElse(null);
    }

    //public String getBankHldayFrom

}
