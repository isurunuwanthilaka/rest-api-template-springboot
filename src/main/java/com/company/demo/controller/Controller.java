package com.company.demo.controller;

import com.company.demo.exception.RequestValidationError;
import com.company.demo.service.IService;
import com.company.demo.util.TransportDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@Validated
@RestController
@RequestMapping("v1")
@Api(value = "ping")
public class Controller {

    private IService service;

    @Autowired
    public Controller(IService service) {
        this.service = service;
    }

    @GetMapping("/ping")
    @ApiOperation(value = "ping")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "ping", response = String.class)})
    public @ResponseBody
    ResponseEntity ping() throws RequestValidationError {

        TransportDto res = service.ping();

        if (res.getError() != null) {
            return ResponseEntity.status(res.getError().getHttpStatus()).body(res);
        }
        return ResponseEntity.ok(res.getResponse());
    }

}
