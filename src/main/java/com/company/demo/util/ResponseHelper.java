package com.company.demo.util;

import com.company.demo.exception.ApiError;
import com.company.demo.exception.ErrorMessage;
import org.slf4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class ResponseHelper {

    public TransportDto setResponse(Object response) {
        TransportDto dto = new TransportDto();
        dto.setResponse(response);
        return dto;
    }

    public TransportDto setResponse(Object response, Logger log, String logMessage,
                                    Object... objects) {
        TransportDto dto = new TransportDto();
        log.info(logMessage, objects);
        dto.setResponse(response);
        return dto;
    }

    public TransportDto setError(HttpStatus status, ErrorMessage error, Logger log, String logMessage,
                                 Object... objects) {
        TransportDto dto = new TransportDto();
        log.error(logMessage, objects);
        ApiError apiError = new ApiError(status);
        apiError.setMessage(error.getErrorMessage());
        dto.setError(apiError);
        return dto;
    }

    public TransportDto setError(HttpStatus status, String debugMessage, String message) {
        TransportDto dto = new TransportDto();
        ApiError apiError = new ApiError(status);
        apiError.setMessage(message);
        apiError.setDebugMessage(debugMessage);
        dto.setError(apiError);
        return dto;
    }

}
