package com.intranet_F5.Services;

import com.intranet_F5.Repository.RequestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RequestService {
    @Autowired
    RequestRepository requestRepository;
}
