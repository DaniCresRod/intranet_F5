package com.intranet_F5.Services;

import com.intranet_F5.Repository.LogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public class LogService  {
    @Autowired
    LogRepository logRepository;
}
