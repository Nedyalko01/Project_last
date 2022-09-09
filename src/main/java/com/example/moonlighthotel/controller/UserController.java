package com.example.moonlighthotel.controller;


import com.example.moonlighthotel.converter.UserConverter;
import com.example.moonlighthotel.dto.UserRequest;
import com.example.moonlighthotel.dto.UserResponse;
import com.example.moonlighthotel.exeptions.UserNotFoundException;
import com.example.moonlighthotel.model.User;
import com.example.moonlighthotel.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashSet;
import java.util.Set;

import static com.example.moonlighthotel.constant.ExceptionConstant.BAD_CREDENTIALS;

@RestController
@RequestMapping(value = "/users")
public class UserController {

    private final UserServiceImpl userServiceImpl;

    @Autowired
    public UserController(UserServiceImpl userServiceImpl) {
        this.userServiceImpl = userServiceImpl;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<UserResponse> register(@Valid @RequestBody UserRequest userRequest) {
        User newUser =  userServiceImpl.register(userRequest);

        UserResponse responseUser = UserConverter.convertToUserDto(newUser);
        return new ResponseEntity<>(responseUser, HttpStatus.CREATED);
    }

    //@PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<String> deleteById(@PathVariable Long id) {

        try {
            userServiceImpl.deleteUserById(id);

            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {

            throw new UserNotFoundException(BAD_CREDENTIALS);
        }
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @GetMapping(value = "/{id}")
    public ResponseEntity<UserResponse> findById(@PathVariable Long id) {

        UserResponse user = UserConverter.convertToUserDto(userServiceImpl.findUserById(id));

        return new ResponseEntity<>(user, HttpStatus.OK);
    }


    @GetMapping
    public ResponseEntity<Set<UserResponse>> findAll() {

        Set<UserResponse> userResponses = new HashSet<>();

        for (User user : userServiceImpl.getUsers()) {
            userResponses.add(UserConverter.convertToUserDto(user));
        }

        return ResponseEntity.ok(userResponses);
    }


    @PostMapping(value = "/{id}")
    public ResponseEntity<UserResponse> update(@PathVariable Long id, @RequestBody UserRequest userRequest) {

        UserResponse user = UserConverter.convertToUserDto(userServiceImpl.updateUser(id, userRequest));

        return new ResponseEntity<>(user, HttpStatus.OK);
    }
}

