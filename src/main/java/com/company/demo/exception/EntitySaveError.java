package com.company.demo.exception;

public class EntitySaveError extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public EntitySaveError(String message) {
        super(message);
    }
}
