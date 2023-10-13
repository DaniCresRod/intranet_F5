package com.intranet_F5.Services;

import com.intranet_F5.DTO.request.ChangeStatus;
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

    //Faltaria filtro para evitar estatus incorrectos
    public String newRequest(UserRequestModel newRequest) {
        try{
            if (requestRepository.findRepeatedDateRequests(newRequest) == null) {

                requestRepository.save(newRequest);

                //Crear el log de creacion de la petición
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

    //Un request solo permitira cambiar fechas, status y userReason.
    public UserRequestModel updateRequest(long id, UserRequestModel updatedRequest) {
        try{
            if(requestRepository.existsById(id)){
                UserRequestModel myRequest=requestRepository.findById(id).get();
                myRequest.setStartDate(updatedRequest.getStartDate());
                myRequest.setEndDate(updatedRequest.getEndDate());
                myRequest.setUserReason(updatedRequest.getUserReason());

                myRequest.setStatus(updatedRequest.getStatus()); //Faltaria filtro para evitar estatus incorrectos

                requestRepository.save(myRequest);

                userService.HldyDaysLeft( myRequest.getUserId().getId() , updatedRequest);

                //Crear el log de creacion de la petición
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

    //EntryPoint para solicitudes sencillas de cambio de status
    public UserRequestModel updateRequestStatusOnly(long id, ChangeStatus updatedRequestStatus) {
        try{
            if(requestRepository.existsById(id)){
                UserRequestModel myRequest=requestRepository.findById(id).get();
                myRequest.setStatus(updatedRequestStatus.getStatus()); //Faltaria filtro para evitar estatus incorrectos
                requestRepository.save(myRequest);

                userService.HldyDaysLeft( myRequest.getUserId().getId() , myRequest);
                try{
                    if(updatedRequestStatus.getStatus()==2){
                        LogService.createLog("Se ha aceptado la peticion", myRequest.getId());
                    }
                    else if(updatedRequestStatus.getStatus()==3){
                        LogService.createLog("Se ha rechazado la petición"
                                +" (Motivo: "
                                +updatedRequestStatus.getReason()
                                +")", myRequest.getId());
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
