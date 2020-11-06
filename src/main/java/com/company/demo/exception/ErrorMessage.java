package com.company.demo.exception;

import lombok.Getter;

public enum ErrorMessage {

    PING_FAILED("Ping failed.");

    @Getter
    private String error;

    ErrorMessage(String err) {
        this.error = err;
    }
}
