package com.intranet_F5.Controller;

import com.intranet_F5.Model.SchoolModel;
import com.intranet_F5.Model.UserRequestModel;
import com.intranet_F5.Services.RequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path="/request")
@CrossOrigin(origins = "*")
public class RequestController {

    @Autowired
    RequestService requestService;

    @GetMapping
    public List<UserRequestModel> getAllRequests(){
        return requestService.getAllRequests();
    }

    @GetMapping(path = "/{id}")
    public UserRequestModel getOneRequestById(@PathVariable long id){
        return requestService.getOneRequestById(id);
    }

    @GetMapping(path = "/school/{id}")
    public List<UserRequestModel> getOneRequestBySchoolId(@PathVariable long id){
        return requestService.getOneRequestBySchoolId(id);
    }

    @PostMapping
    public String newRequest(@RequestBody UserRequestModel newRequest){
        return requestService.newRequest(newRequest);
    }

}
