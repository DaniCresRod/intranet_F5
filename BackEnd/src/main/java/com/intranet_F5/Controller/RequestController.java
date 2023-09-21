package com.intranet_F5.Controller;

import com.intranet_F5.Services.RequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path="/request")
@CrossOrigin(origins = "*")
public class RequestController {

    @Autowired
    RequestService requestService;
}
