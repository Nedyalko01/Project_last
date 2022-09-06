package com.example.moonlighthotel.exeptions;

public class InvalidPhoneNumber extends RuntimeException {

    public InvalidPhoneNumber(String message) {
        super(message);
    }
}


