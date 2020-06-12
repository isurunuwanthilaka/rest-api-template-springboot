package com.company.demo.util;

import com.company.demo.exception.ApiError;
import com.company.demo.exception.ErrorMessage;
import org.slf4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class RequestHelper {

    public TransportDto setError(ErrorMessage error, Logger log, String logMessage, Object... objects) {
        return setError(HttpStatus.BAD_REQUEST, error.getErrorMessage(), log, logMessage, objects);
    }

    public TransportDto setError(String error, Logger log, String logMessage, Object... objects) {
        return setError(HttpStatus.BAD_REQUEST, error, log, logMessage, objects);
    }

    public TransportDto setError(HttpStatus status, ErrorMessage error, Logger log, String logMessage, Object... objects) {
        return this.setError(status, error.getErrorMessage(), log, logMessage, objects);
    }

    public TransportDto setError(HttpStatus status, String error, Logger log, String logMessage, Object... objects) {
        log.error(logMessage, objects);
        TransportDto dto = new TransportDto();
        ApiError apiError = new ApiError(status);
        apiError.setMessage(error);
        dto.setError(apiError);
        return dto;
    }

    public TransportDto setResponse(Object response) {
        TransportDto dto = new TransportDto();
        dto.setResponse(response);
        return dto;
    }

    public TransportDto setResponse(Object response, Logger log, String logMessage, Object... objects) {
        log.info(logMessage, objects);
        TransportDto dto = new TransportDto();
        dto.setResponse(response);
        return dto;
    }

}
