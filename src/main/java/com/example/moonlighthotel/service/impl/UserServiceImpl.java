package com.example.moonlighthotel.service.impl;

import com.example.moonlighthotel.configuration.PasswordEncoder;
import com.example.moonlighthotel.converter.UserConverter;
import com.example.moonlighthotel.dto.EmailRequest;
import com.example.moonlighthotel.dto.user.PasswordResetRequest;
import com.example.moonlighthotel.dto.user.UserRequest;
import com.example.moonlighthotel.exeptions.RecordNotFoundException;
import com.example.moonlighthotel.exeptions.UserNotFoundException;
import com.example.moonlighthotel.model.User;
import com.example.moonlighthotel.repositories.UserRepository;
import com.example.moonlighthotel.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;

import static com.example.moonlighthotel.constant.EmailConstant.EMAIL_FORGOT_PASSWORD;
import static com.example.moonlighthotel.constant.ExceptionConstant.BAD_CREDENTIALS;
import static com.example.moonlighthotel.constant.ExceptionConstant.USER_NOT_FOUND;

@Service
public class UserServiceImpl  implements UserService, UserDetailsService {
    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User register(UserRequest userRequest) {

        User user = UserConverter.convertToUser(userRequest);
        userRepository.save(user);

        return user;
    }

    @Override
    public User findUserById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(String.format(USER_NOT_FOUND, id)));
    }

    @Override
    public Set<User> getUsers() {
        return new HashSet<>(userRepository.findAll());
    }

    @Override
    public User updateUser(Long id, UserRequest userRequest) {

        User user = findUserById(id);
        userRepository.save(UserConverter.convertToUser(userRequest));

        return user;
    }

    public User swap (Long id, User update) {
        User dbRole = findUserById(id);
        dbRole.setFirstName(update.getFirstName());
        return userRepository.save(dbRole);
    }

    public User findByEmail(String email) {
        Objects.requireNonNull(email);
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new RecordNotFoundException(
                        String.format("User with email:%s, not found.", email)));
    }

    @Override
    public void deleteUserById(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public User loadUserByUsername(String username) {

        return userRepository.findByEmail(username)
                .orElseThrow(() -> new BadCredentialsException(BAD_CREDENTIALS));
    }

    public void resetPassword(PasswordResetRequest passwordResetRequest) {

        User user = loadUserByUsername(passwordResetRequest.getEmail());

        if (PasswordEncoder.encoder().matches(passwordResetRequest.getToken(), user.getPassword())) {

            String newPassword = PasswordEncoder.encoder().encode(passwordResetRequest.getPassword());

            user.setPassword(newPassword);

            userRepository.save(user);

        } else {

            throw new BadCredentialsException("Token does not match!");
        }

    }

    public void forgotPassword(EmailRequest emailRequest) {

        User user = loadUserByUsername(emailRequest.getEmail());

        String newPassword = generateToken();

        user.setPassword(PasswordEncoder.encodePassword(newPassword));

        userRepository.save(user);

        String message = String.format(EMAIL_FORGOT_PASSWORD, user.getFirstName(), newPassword);


    }


    private String generateToken() {

        return UUID.randomUUID().toString();
    }

}
