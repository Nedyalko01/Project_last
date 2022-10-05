package com.example.moonlighthotel.controller;


import com.example.moonlighthotel.converter.UserConverter;
import com.example.moonlighthotel.dto.user.UserResponse;
import com.example.moonlighthotel.model.AuthenticationRequest;
import com.example.moonlighthotel.model.AuthenticationResponse;
import com.example.moonlighthotel.service.impl.LoginService;
import com.example.moonlighthotel.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/users")
public class LoginController {

  private final UserServiceImpl userService;

  private final LoginService loginService;

  @Autowired
  public LoginController( LoginService loginService, UserServiceImpl userService) {
    this.userService = userService;
    this.loginService = loginService;
  }

  @RequestMapping(value = "/token", method = RequestMethod.POST)
  public ResponseEntity<AuthenticationResponse> login (@RequestBody AuthenticationRequest authenticationRequest) throws Exception{

    String token = loginService.login(authenticationRequest);

    UserResponse user = UserConverter.convertToUserDto(userService.loadUserByUsername(authenticationRequest.getUsername()));

    return new ResponseEntity<>(new AuthenticationResponse(token, user), HttpStatus.OK);

  }
}
