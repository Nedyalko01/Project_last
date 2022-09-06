package com.example.moonlighthotel.service.impl;

import com.example.moonlighthotel.converter.UserConverter;
import com.example.moonlighthotel.dto.UserRequest;
import com.example.moonlighthotel.exeptions.UserNotFoundException;
import com.example.moonlighthotel.model.User;
import com.example.moonlighthotel.repositories.UserRepository;
import com.example.moonlighthotel.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

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

    @Override
    public void deleteUserById(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public User loadUserByUsername(String username) {

        return userRepository.findUserByEmail(username)
                .orElseThrow(() -> new BadCredentialsException(BAD_CREDENTIALS));
    }
}
