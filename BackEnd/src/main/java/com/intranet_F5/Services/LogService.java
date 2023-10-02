package com.intranet_F5.Services;

import com.intranet_F5.Model.LogModel;
import com.intranet_F5.Repository.LogRepository;
import com.intranet_F5.Repository.RequestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LogService  {
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

    public String createLog(LogModel newLog) {
        if(requestRepository.existsById(newLog.getRequestId())){
            logRepository.save(newLog);
            return "Se ha creado un log para la peticion"+
                    " de "+
                    (requestRepository.findById(newLog.getRequestId())).get().getUserReason()+
                    " de "+
                    (requestRepository.findById(newLog.getRequestId())).get().getUserId().getUsername();
        }
        return "No existe una petición a la que añadir el log";
    }
}
