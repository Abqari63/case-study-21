package com.cts.carrentalsystem.exception;

public class InvalidCustomerInformationException extends Exception {
    public InvalidCustomerInformationException(String message) {
        super(message);
    }

    public InvalidCustomerInformationException(String message, Throwable cause) {
        super(message);
    }
}
