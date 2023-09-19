package com.intranet_F5.Controller;

import com.intranet_F5.Services.SchoolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path="/schools")
@CrossOrigin(origins = "*")
public class SchoolController {
    @Autowired
    private SchoolService schoolService;

}
