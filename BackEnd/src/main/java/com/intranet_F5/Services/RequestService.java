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

    @Autowired
    LogService LogService;

    @Autowired
    UserService userService;

    public List<UserRequestModel> getAllRequests() {
        return requestRepository.findAll();
    }

    public String newRequest(UserRequestModel newRequest) {
        try{
            if (requestRepository.findRepeatedDateRequests(newRequest) == null) {

                requestRepository.save(newRequest);

                try{
                    LogService.createLog("Se crea la petición", newRequest.getId());
                }
                catch(Exception e){
                    return "Se salvó la petición pero no se pudo crear el log: "+e.getMessage();
                }
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

    public UserRequestModel updateRequest(long id, UserRequestModel updatedRequest) {
        try{
            if(requestRepository.existsById(id)){
                UserRequestModel myRequest=requestRepository.findById(id).get();
                myRequest.setStartDate(updatedRequest.getStartDate());
                myRequest.setEndDate(updatedRequest.getEndDate());
                myRequest.setUserReason(updatedRequest.getUserReason());
                myRequest.setStatus(updatedRequest.getStatus());

                requestRepository.save(myRequest);

                userService.HldyDaysLeft( myRequest.getUserId().getId() , updatedRequest);

                try{
                    LogService.createLog("Se ha modificado la petición", id);
                }
                catch(Exception e){
                    e.printStackTrace();
                }
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

                userService.HldyDaysLeft( myRequest.getUserId().getId() , myRequest);
                try{
                    if(updatedRequestStatus==2){
                        LogService.createLog("Se ha aceptado la peticion", myRequest.getId());
                    }
                    else if(updatedRequestStatus==3){
                        LogService.createLog("Se ha rechazado la petición"
                                , myRequest.getId());
                    }
                }
                catch(Exception e){
                    e.printStackTrace();
                }

                return myRequest;
            }
            else return null;
        }
        catch(Exception e){
            return null;
        }
    }
}
