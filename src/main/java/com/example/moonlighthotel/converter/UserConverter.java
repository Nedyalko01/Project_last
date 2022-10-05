package com.example.moonlighthotel.converter;

import com.example.moonlighthotel.configuration.PasswordEncoder;
import com.example.moonlighthotel.dto.user.UserRequest;
import com.example.moonlighthotel.dto.user.UserResponse;
import com.example.moonlighthotel.model.Role;
import com.example.moonlighthotel.model.User;


import java.time.Instant;
import java.util.Set;

public class UserConverter {

    public static UserResponse convertToUserDto(User user) {

        Set<String> roles = RolePrefixConverter.removePrefix(user.getAuthorityName());

        return new UserResponse.Builder()
                .addId(user.getId())
                .addEmail(user.getEmail())
                .addName(user.getFirstName())
                .addSurname(user.getLastName())
                .addPhone(user.getPhoneNumber())
                .addRoles(roles)
                .addCreated(user.getCreatedAt())
                .build();
    }

    public static User convertToUser(UserRequest userRequest) {

        String encodedPassword = PasswordEncoder.encodePassword(userRequest.getPassword());

        Set<Role> roles = RoleConverter.convertRoleStringToRole(userRequest.getRoles());

        User user = new User();
        user.setFirstName(userRequest.getName());
        user.setLastName(userRequest.getSurname());
        user.setEmail(userRequest.getEmail());
        user.setPhoneNumber(userRequest.getPhone());
        user.setPassword(encodedPassword);
        user.setCreatedAt(Instant.now());
        user.setRoles(roles);

        return user;
    }
}
