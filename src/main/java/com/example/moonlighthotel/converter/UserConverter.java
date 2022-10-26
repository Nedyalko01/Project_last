package com.example.moonlighthotel.converter;

import com.example.moonlighthotel.configuration.PasswordEncoder;
import com.example.moonlighthotel.dto.user.UserRequest;
import com.example.moonlighthotel.dto.user.UserResponse;
import com.example.moonlighthotel.model.Role;
import com.example.moonlighthotel.model.User;
import com.example.moonlighthotel.validator.UserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.Set;


@Component
public class UserConverter {

    private static UserValidator userValidator;

    @Autowired
    public UserConverter(UserValidator userValidator) {
        UserConverter.userValidator = userValidator;
    }

    public static UserResponse convertToUserResponse(User user) {

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

        User user = new User();

        return convertRequestToUser(user, userRequest);
    }

    public static User update(User user, UserRequest userRequest) {

        return convertRequestToUser(user, userRequest);
    }

    private static User convertRequestToUser(User user, UserRequest userRequest) {

        String encodedPassword = PasswordEncoder.encodePassword(userRequest.getPassword());

        Set<Role> roles = RoleConverter.convertRoleStringToRole(userRequest.getRoles());

        userValidator.validatePhoneNumber(userRequest.getPhone());

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
