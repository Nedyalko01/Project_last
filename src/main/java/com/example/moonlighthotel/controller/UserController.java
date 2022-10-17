package com.example.moonlighthotel.controller;


import com.example.moonlighthotel.converter.RoomReservationConverter;
import com.example.moonlighthotel.converter.UserConverter;
import com.example.moonlighthotel.dto.EmailRequest;
import com.example.moonlighthotel.dto.restaurant.TableReservationResponse;
import com.example.moonlighthotel.dto.user.PasswordResetRequest;
import com.example.moonlighthotel.dto.user.UserRequest;
import com.example.moonlighthotel.dto.user.UserReservationResponse;
import com.example.moonlighthotel.dto.user.UserResponse;
import com.example.moonlighthotel.exeptions.UserNotFoundException;
import com.example.moonlighthotel.model.RoomReservation;
import com.example.moonlighthotel.model.TableReservation;
import com.example.moonlighthotel.model.User;
import com.example.moonlighthotel.service.RoomReservationService;
import com.example.moonlighthotel.service.TableReservationService;
import com.example.moonlighthotel.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static com.example.moonlighthotel.constant.ExceptionConstant.BAD_CREDENTIALS;

@RestController
@RequestMapping(value = "/users")
public class UserController {

    private final UserServiceImpl userServiceImpl;
    private final RoomReservationService roomReservationService;
    private final TableReservationService tableReservationService;

    @Autowired
    public UserController(UserServiceImpl userServiceImpl, RoomReservationService roomReservationService, TableReservationService tableReservationService) {
        this.userServiceImpl = userServiceImpl;
        this.roomReservationService = roomReservationService;
        this.tableReservationService = tableReservationService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<UserResponse> register(@Valid @RequestBody UserRequest userRequest) {
        User newUser =  userServiceImpl.register(userRequest);

        UserResponse responseUser = UserConverter.convertToUserDto(newUser);
        return new ResponseEntity<>(responseUser, HttpStatus.CREATED);
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
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


    @PostMapping(value = "/reset")
    public ResponseEntity<HttpStatus> resetPassword(@RequestBody PasswordResetRequest passwordResetRequest){
        userServiceImpl.resetPassword(passwordResetRequest);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PostMapping(value = "/forgot")
    public ResponseEntity<HttpStatus> forgotPassword(@RequestBody EmailRequest emailRequest) {

        userServiceImpl.forgotPassword(emailRequest);

        return new ResponseEntity<>(HttpStatus.OK);

    }

    @GetMapping(value = "/reservations")
    public ResponseEntity<List<UserReservationResponse>> getReservations() {

        List<RoomReservation> reservations = roomReservationService.getAll();

        List<UserReservationResponse> reservationResponses = reservations
                .stream()
                .map(RoomReservationConverter::convertToUserReservationResponse)
                .collect(Collectors.toList());

        return new ResponseEntity<>(reservationResponses, HttpStatus.OK);

    }
    @GetMapping(value = "/{id}/reservations")
    public ResponseEntity<List<UserReservationResponse>> getReservationsByUserId(@PathVariable Long id) {

        User user = userServiceImpl.findUserById(id);

        List<RoomReservation> reservations = roomReservationService.getByUserId(user);

        List<UserReservationResponse> userReservationResponses = reservations
                .stream()
                .map(RoomReservationConverter::convertToUserReservationResponse)
                .collect(Collectors.toList());

        return new ResponseEntity<>(userReservationResponses, HttpStatus.OK);

    }


    @GetMapping(value = "/{id}/reservations/{rid}")
    public ResponseEntity<UserReservationResponse> getReservationByIdAndUserId(@PathVariable Long uid,
                                                                               @PathVariable Long rid) {

        RoomReservation roomReservation = roomReservationService.findReservationByIdAndUserId(uid, rid);

        UserReservationResponse userReservationResponse = RoomReservationConverter.convertToUserReservationResponse(roomReservation);

        return new ResponseEntity<>(userReservationResponse, HttpStatus.OK);

    }

    @GetMapping(value = "/{id}/tables/reservations")
    public ResponseEntity<List<TableReservationResponse>> getTableReservationsByUser(@PathVariable Long id) {

        List<TableReservation> tableReservations = tableReservationService.getTableReservationsByUser(id);

        List<TableReservationResponse> response = tableReservations
                .stream()
                .map(tableReservation -> TableReservationConverter.convertToTableReservationResponse(tableReservation))
                .collect(Collectors.toList());

        return new ResponseEntity<>(response, HttpStatus.OK);

    }


}

