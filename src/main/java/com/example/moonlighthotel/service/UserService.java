package com.example.moonlighthotel.service;

import com.example.moonlighthotel.dto.user.UserRequest;
import com.example.moonlighthotel.model.User;

import java.util.Set;

public interface UserService {

    User register(UserRequest userRequest);

    User findUserById(Long id);

    Set<User> getUsers();

    User updateUser(Long id, UserRequest userRequest);

    void deleteUserById(Long id);

    //void resetPassword(String email);
}


