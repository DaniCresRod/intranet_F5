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

    //Faltaria filtro para evitar estatus incorrectos
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

    //Un request solo permitira cambiar fechas, status y userReason.
    public UserRequestModel updateRequest(long id, UserRequestModel updatedRequest) {
        try{
            if(requestRepository.existsById(id)){
                UserRequestModel myRequest=requestRepository.findById(id).get();
                myRequest.setStartDate(updatedRequest.getStartDate());
                myRequest.setEndDate(updatedRequest.getEndDate());
                myRequest.setStatus(updatedRequest.getStatus()); //Faltaria filtro para evitar estatus incorrectos
                myRequest.setUserReason(updatedRequest.getUserReason());

                requestRepository.save(myRequest);
                return myRequest;
            }
            else return null;
        }
        catch(Exception e){
            return null;
        }
    }

    public UserRequestModel updateRequestStatusOnly(long id, int updatedRequestStatus) {
        try{
            if(requestRepository.existsById(id)){
                UserRequestModel myRequest=requestRepository.findById(id).get();
                myRequest.setStatus(updatedRequestStatus); //Faltaria filtro para evitar estatus incorrectos
                requestRepository.save(myRequest);
                return myRequest;
            }
            else return null;
        }
        catch(Exception e){
            return null;
        }
    }
}
