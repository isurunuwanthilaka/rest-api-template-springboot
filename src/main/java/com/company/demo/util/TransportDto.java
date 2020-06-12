package com.company.demo.util;

import com.company.demo.exception.ApiError;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TransportDto {

    private ApiError error;
    private Object response;

}
