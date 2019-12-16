package com.pjtest.app;

public class InvalidMatrixException extends RuntimeException {    

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    public InvalidMatrixException() {
        super("Invalid Matrix Format");
    }
}