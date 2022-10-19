package com.example.moonlighthotel.exeptions;

public class EmailNotSentException extends RuntimeException{

    public EmailNotSentException() {
}

    public EmailNotSentException(String message) {
        super(message);
    }
}
