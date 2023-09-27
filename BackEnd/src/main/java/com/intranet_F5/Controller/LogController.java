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

    //No deberia existir esta posibilidad
//    @DeleteMapping(path="/{id}")
//    public String deleteLog(@PathVariable long id){
//        return logService.deleteLog(id);
//    }

    @PostMapping(path="/add")
    public String createLog(@RequestBody LogModel newLog){
        return logService.createLog(newLog);
    }
}
