package com.intranet_F5.Services;

import com.intranet_F5.Model.LogModel;
import com.intranet_F5.Model.UserModel;
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

    public String createLog(String newLogText, long fatherRequestId) {

        if(requestRepository.existsById(fatherRequestId)){
            String requestUserName = (requestRepository.findById(fatherRequestId)).get().getUserId().getUsername() != null ?
                    (requestRepository.findById(fatherRequestId)).get().getUserId().getUsername() :
                    loggedUser.getUsername();

            LogModel newLog=new LogModel();
            newLog.setRequestId(fatherRequestId);
            newLog.setLogDate(LocalDate.now());
            newLog.setLogText(newLogText
                    + " para "+(requestRepository.findById(fatherRequestId)).get().getUserReason()
                    +" de "+requestUserName
                    +", supervisado por "+loggedUser.getUsername()
                    +" "+loggedUser.getUserSurName()
                    +"."
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
