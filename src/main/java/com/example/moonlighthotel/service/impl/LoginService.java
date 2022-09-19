package com.example.moonlighthotel.service.impl;


import com.example.moonlighthotel.model.AuthenticationRequest;
import com.example.moonlighthotel.util.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.validation.constraints.NotNull;

import static com.example.moonlighthotel.constant.SecurityConstant.BLANK_USERNAME_OR_PASSWORD;
import static com.example.moonlighthotel.constant.SecurityConstant.INVALID_USERNAME_OR_PASSWORD;

@Service
public class LoginService {

    private final AuthenticationManager authenticationManager;

    private final UserServiceImpl userService;

    private final JwtTokenUtil jwtTokenUtil;

    @Autowired
    public LoginService(AuthenticationManager authenticationManager, UserServiceImpl userService, JwtTokenUtil jwtTokenUtil) {
        this.authenticationManager = authenticationManager;
        this.userService = userService;
        this.jwtTokenUtil = jwtTokenUtil;
    }


    public String login(@NotNull AuthenticationRequest authenticationRequest) throws Exception {

        if (!StringUtils.hasText(authenticationRequest.getUsername())
                || !StringUtils.hasText(authenticationRequest.getUsername())) {
            throw new BadCredentialsException(BLANK_USERNAME_OR_PASSWORD);

        }

        authenticate(authenticationRequest.getUsername(), authenticationRequest.getPassword());

        final UserDetails userDetails = userService.loadUserByUsername(authenticationRequest.getUsername());

        return jwtTokenUtil.generateToken(userDetails);
    }


    private void authenticate(String username, String password) throws Exception {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        } catch (BadCredentialsException ex) {
            throw new Exception(INVALID_USERNAME_OR_PASSWORD, ex);
        }

    }
}