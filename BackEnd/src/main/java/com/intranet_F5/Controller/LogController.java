package com.intranet_F5.Controller;

import com.intranet_F5.Model.LogModel;
import com.intranet_F5.Services.LogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path="/logs")
@CrossOrigin(origins = "*")
public class LogController {
    @Autowired
    LogService logService;

    @GetMapping
    public List<LogModel> getAllLogs(){
        return logService.getAllLogs();
    }

    @GetMapping(path = "/{id}")
    public List<LogModel> getAllLogsFromRequestId(@PathVariable long id){
        return logService.getAllLogsFromRequestId(id);
    }

    @PostMapping(path="/add/{id}")
    public String createLog(@RequestBody String newLog, @PathVariable long id){
        return logService.createLog(newLog, id);
    }
}
