package com.company.demo.service.impl;

import com.company.demo.service.IService;
import com.company.demo.util.RequestHelper;
import com.company.demo.util.TransportDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ServiceImpl implements IService {

    private final Logger logger = LoggerFactory.getLogger(ServiceImpl.class);

    private RequestHelper requestHelper;

    @Autowired
    public ServiceImpl(RequestHelper requestHelper) {
        this.requestHelper = requestHelper;
    }

    public TransportDto ping() {

        /**
         * ping
         */

        try {
            return requestHelper.setResponse("pong");
        } catch (Exception e) {
            return requestHelper.setError("pong not found", logger, "pong not found");
        }
    }

}
