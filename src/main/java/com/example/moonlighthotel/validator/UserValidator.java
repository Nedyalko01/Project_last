package com.example.moonlighthotel.validator;


import com.example.moonlighthotel.exeptions.InvalidPhoneNumber;
import com.example.moonlighthotel.exeptions.RecordNotFoundException;
import com.example.moonlighthotel.model.User;
import com.example.moonlighthotel.repositories.UserRepository;
import com.example.moonlighthotel.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


import static com.example.moonlighthotel.constant.ExceptionConstant.*;
import static com.example.moonlighthotel.constant.ValidationConstant.INVALID_PHONE;

@Component
public class UserValidator {

    @Autowired
    private final UserRepository userRepository;
    private final UserServiceImpl userService;

    public UserValidator(UserRepository userRepository, UserServiceImpl userService) {
        this.userRepository = userRepository;
        this.userService = userService;
    }

    public void validateUserById(Long uid) {

        userRepository.findById(uid)
                .orElseThrow(() -> new RecordNotFoundException(USER_NOT_FOUND));
    }

    public void validateUserByEmail(String email) {

        userRepository.findByEmail(email)
                .orElseThrow(() -> new RecordNotFoundException(String.format(USER_EMAIL_NOT_FOUND, email)));
    }

    public void validateEmailDuplicates(String email) {

        User user = userService.loadUserByUsername(email);

        if (user != null) {
            throw new RuntimeException(String.format(EMAIL_EXIST, email));
        }
    }

    public void validatePhoneNumber(String phoneNumber) {

        if (!phoneNumber.startsWith("+") && !phoneNumber.startsWith("00")) {
            throw new InvalidPhoneNumber(INVALID_PHONE);
        }
    }
}


