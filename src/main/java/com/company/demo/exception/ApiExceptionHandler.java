package com.company.demo.exception;

import com.company.demo.util.ResponseHelper;
import com.company.demo.util.TransportDto;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.validation.ValidationException;
import java.util.NoSuchElementException;

@ControllerAdvice(value = "com.company.demo.controller")
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {

    public ResponseHelper responseHelper;

    @Autowired
    public ApiExceptionHandler(ResponseHelper responseHelper) {
        this.responseHelper = responseHelper;
    }

    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    @ResponseBody
    public TransportDto handleConstraintViolationException(ConstraintViolationException exception) {
        return responseHelper.setError(HttpStatus.BAD_REQUEST, String.valueOf(exception.getStackTrace()), exception.getMessage());
    }

    @ExceptionHandler(ValidationException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    @ResponseBody
    public TransportDto handleValidationException(ValidationException exception) {
        String msg = "ValidationException";
        return responseHelper.setError(HttpStatus.BAD_REQUEST, String.valueOf(exception.getMessage()), msg);
    }

    @ExceptionHandler(RequestValidationError.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    @ResponseBody
    public TransportDto handleRequestValidationError(RequestValidationError exception) {
        return responseHelper.setError(HttpStatus.BAD_REQUEST, String.valueOf(exception.getMessage()), "RequestValidationError");
    }

    @ExceptionHandler(NoSuchElementException.class)
    @ResponseStatus(value = HttpStatus.ACCEPTED)
    @ResponseBody
    public TransportDto handleNoSuchElementException(NoSuchElementException exception) {
        return responseHelper.setError(HttpStatus.ACCEPTED, String.valueOf(exception.getMessage()), "NoSuchElementException");
    }

    //overriding method this method as it is in the Exception class, this is for incoming request parameter validation
    @Override
    public ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException exception, HttpHeaders headers, HttpStatus status, WebRequest request) {

        String msg = exception.getBindingResult()
                .getFieldErrors()
                .stream().map(err -> {
                    return String.format("%s %s", err.getField(), err.getDefaultMessage());
                })
                .collect(java.util.stream.Collectors.joining(", "));


        TransportDto res = responseHelper.setError(HttpStatus.BAD_REQUEST, "MethodArgumentNotValid", msg);
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(res);
    }


}
