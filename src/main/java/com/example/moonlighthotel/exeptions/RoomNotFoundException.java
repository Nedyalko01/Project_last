package com.example.moonlighthotel.exeptions;

public class RoomNotFoundException extends RuntimeException{

    public RoomNotFoundException(String message) {
        super(message);
    }
}
