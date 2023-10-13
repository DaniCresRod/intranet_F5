package com.intranet_F5.Services;

import com.intranet_F5.Model.LogModel;
import com.intranet_F5.Model.UserModel;
import com.intranet_F5.Model.UserRequestModel;
import com.intranet_F5.Repository.LogRepository;
import com.intranet_F5.Repository.RequestRepository;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@Getter
@Setter
public class LogService  {

    private UserModel loggedUser;

    @Autowired
    LogRepository logRepository;

    @Autowired
    RequestRepository requestRepository;

    public List<LogModel> getAllLogs() {
        return logRepository.findAll();
    }

    public List<LogModel> getAllLogsFromRequestId(long id) {
        return logRepository.findAllByRequestId(id);
    }

//    public String deleteLog(long id) {
//        try{
//            if(logRepository.existsById(id)){
//                logRepository.deleteById(id);
//                return "El log se borró correctamente";
//            }
//            else return "No se encontró el log solicitado";
//
//        }
//        catch(Exception e){
//            return "Hubo un error al procesar la solicitud de borrado de log";
//        }
//    }

//    public String createLog(LogModel newLog) {
//        if(requestRepository.existsById(newLog.getRequestId())){
//            logRepository.save(newLog);
//            return "Se ha creado un log para la peticion"+
//                    " de "+
//                    (requestRepository.findById(newLog.getRequestId())).get().getUserReason()+
//                    " de "+
//                    (requestRepository.findById(newLog.getRequestId())).get().getUserId().getUsername();
//        }
//        return "No existe una petición a la que añadir el log";
//    }

    //El creador del log es el usuario que este registrado en ese momento
    public String createLog(String newLogText, long fatherRequestId) {

        if(requestRepository.existsById(fatherRequestId)){
            //Cuando la solicitud es la inicial, la request aun no esta creada, y es posible que en la solicitud
            //no figure el nombre (por ejemplo desde postman). Desde el front si que lo trae
            String requestUserName = (requestRepository.findById(fatherRequestId)).get().getUserId().getUsername() != null ?
                    (requestRepository.findById(fatherRequestId)).get().getUserId().getUsername() :
                    loggedUser.getUsername();

            LogModel newLog=new LogModel();
            newLog.setRequestId(fatherRequestId);
            newLog.setLogDate(LocalDate.now());
            newLog.setLogText(newLogText
                    + " para "+(requestRepository.findById(fatherRequestId)).get().getUserReason()
                    +" de "+requestUserName
                    +", creado por "+loggedUser.getUsername()
                    +" "+loggedUser.getUserSurName()
                    );
            newLog.setUserId(loggedUser.getId());

            logRepository.save(newLog);
            return "Se ha creado un log para la peticion"+
                    " de "+
                    (requestRepository.findById(fatherRequestId)).get().getUserReason()+
                    " de "+
                    (requestRepository.findById(fatherRequestId)).get().getUserId().getUsername();
        }
        return "No existe una petición a la que añadir el log";
    }
}
