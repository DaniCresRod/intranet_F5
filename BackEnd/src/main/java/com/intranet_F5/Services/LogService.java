package com.intranet_F5.Services;

import com.intranet_F5.Model.LogModel;
import com.intranet_F5.Repository.LogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LogService  {
    @Autowired
    LogRepository logRepository;

    public List<LogModel> getAllLogs() {
        return logRepository.findAll();
    }

    public List<LogModel> getAllLogsFromRequest(long id) {
        return logRepository.findAllByRequestId(id);
    }
}
