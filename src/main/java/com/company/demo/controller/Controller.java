package com.company.demo.controller;

import com.company.demo.exception.RequestValidationError;
import com.company.demo.service.IService;
import com.company.demo.util.TransportDto;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
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

import static com.netflix.hystrix.contrib.javanica.conf.HystrixPropertiesManager.EXECUTION_ISOLATION_THREAD_TIMEOUT_IN_MILLISECONDS;

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

    //hystrix sample
    @GetMapping(value = "/hystrix")
    @HystrixCommand(
            fallbackMethod = "fallbackHystrix",
            commandProperties = {
            @HystrixProperty(name = EXECUTION_ISOLATION_THREAD_TIMEOUT_IN_MILLISECONDS, value = "1000")
    })
    public String testHystrix() throws InterruptedException {
        Thread.sleep(3000);
        return "Welcome Hystrix";
    }
    private String fallbackHystrix() {
        return "Request fails. It takes long time to response";
    }

}
