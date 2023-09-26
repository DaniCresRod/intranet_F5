package com.intranet_F5.Services;

import com.intranet_F5.Model.UserRequestModel;
import com.intranet_F5.Repository.RequestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RequestService {
    @Autowired
    RequestRepository requestRepository;

    public List<UserRequestModel> getAllRequests() {
        return requestRepository.findAll();
    }

    public String newRequest(UserRequestModel newRequest) {
        try{
            if (requestRepository.findRepeatedDateRequests(newRequest) == null) {
                requestRepository.save(newRequest);
                return "Se ha salvado la petición "+newRequest.getId()+" de forma satisfactoria";
            }
            return "Ya existen peticiones en esa fecha";
        }
        catch(Exception e){
            return "Hubo un error al salvar";
        }
    }

    public UserRequestModel getOneRequestById(long id) {
        return requestRepository.findById(id).orElse(null);
    }


    public List<UserRequestModel> getOneRequestBySchoolId(long SchoolId) {
        return requestRepository.findBySchoolId(SchoolId);
    }
}
