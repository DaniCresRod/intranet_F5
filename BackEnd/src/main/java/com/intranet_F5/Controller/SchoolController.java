package com.intranet_F5.Controller;

import com.intranet_F5.Model.SchoolModel;
import com.intranet_F5.Services.SchoolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path="/schools")
@CrossOrigin(origins = "*")
public class SchoolController {
    @Autowired
    private SchoolService schoolService;

    @GetMapping
    public List<SchoolModel> getAllSchools(){
        return schoolService.getAllSchools();
    }

    @GetMapping(path="{id}")
    public SchoolModel getOneSchoolById(@PathVariable long id){
        return schoolService.getOneSchoolById(id);
    }

    @PostMapping
    public String createSchool(@RequestBody SchoolModel newSchool){
        return schoolService.createSchool(newSchool);
    }

    @DeleteMapping(path="/delete/{id}")
    public String deleteSchool(@PathVariable long id){
        return schoolService.deleteSchool(id);
    }

    @PutMapping(path="/update/{id}")
    public SchoolModel updateSchool(@RequestBody SchoolModel updatedSchool, @PathVariable long id){
        return schoolService.upgradeSchool(id, updatedSchool);
    }

}
