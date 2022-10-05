package com.example.moonlighthotel.model;

import com.example.moonlighthotel.dto.user.UserResponse;
import org.springframework.beans.factory.annotation.Autowired;

public class AuthenticationResponse {

    private final String token;

    private final UserResponse user;

    @Autowired
    public AuthenticationResponse(String token, UserResponse user) {
        this.token = token;
        this.user = user;
    }

    public String getToken() {
        return token;
    }

    public UserResponse getUser() {
        return user;
    }
}
