package com.example.moonlighthotel.controller;


import com.example.moonlighthotel.converter.RoomConverter;
import com.example.moonlighthotel.converter.RoomReservationConverter;
import com.example.moonlighthotel.dto.room.AvailableRoomRequest;
import com.example.moonlighthotel.dto.room.RoomRequest;
import com.example.moonlighthotel.dto.room.RoomResponse;
import com.example.moonlighthotel.dto.roomreservation.RoomReservationRequest;
import com.example.moonlighthotel.dto.roomreservation.RoomReservationResponse;
import com.example.moonlighthotel.exeptions.RoomNotFoundException;
import com.example.moonlighthotel.model.Room;
import com.example.moonlighthotel.model.RoomReservation;
import com.example.moonlighthotel.service.RoomReservationService;
import com.example.moonlighthotel.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

import static com.example.moonlighthotel.constant.ExceptionConstant.ROOM_NOT_FOUND;

@RestController
@RequestMapping(value = "/rooms")
public class RoomController {

    private final RoomService roomService;
    private final RoomReservationService roomReservationService;


    @Autowired
    public RoomController(RoomService roomService, RoomReservationService roomReservationService) {
        this.roomService = roomService;
        this.roomReservationService = roomReservationService;

    }

    @PostMapping
    public ResponseEntity<RoomResponse> createRoom(@RequestBody RoomRequest request) {

        Room room = RoomConverter.convertToRoom(request);

        roomService.save(room);

        RoomResponse roomResponse = RoomConverter.convertToRoomResponse(room);

        return new ResponseEntity<>(roomResponse, HttpStatus.CREATED);

    }

    @PostMapping(value = "/{id}/reservations")
    public ResponseEntity<RoomReservationResponse> createRoomReservation(@PathVariable Long id,
                                                                         @RequestBody RoomReservationRequest request) {

        RoomReservation roomReservation = RoomReservationConverter.convertToRoomReservation(id, request);
        roomReservationService.save(roomReservation);

        RoomReservationResponse roomReservationResponse = RoomReservationConverter.
                convertToRoomReservationResponse(id, roomReservation);

        return new ResponseEntity<>(roomReservationResponse, HttpStatus.CREATED);

    }

    //@PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @GetMapping(value = "/{id}")
    public ResponseEntity<RoomResponse> findById(@PathVariable Long id) {

        RoomResponse room = RoomConverter.convertToRoomResponse(roomService.findRoomById(id));

        return new ResponseEntity<>(room, HttpStatus.OK);
    }

    //@PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @PutMapping(value = "/{id}")
    public ResponseEntity<RoomResponse> updateRoom(@PathVariable Long id, @RequestBody RoomRequest request) {

        Room room = roomService.updateRoom(id, request);

        RoomResponse response = RoomConverter.convertToRoomResponse(room);

        return new ResponseEntity<>(response, HttpStatus.OK);

    }

    //@PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<String> deleteById(@PathVariable Long id) {

        try {
            roomService.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception ex) {
            throw new RoomNotFoundException(String.format(ROOM_NOT_FOUND, id));
        }
    }


    //@PreAuthorize("hasAnyRole('ROLE_CLIENT')")
    @GetMapping
    public ResponseEntity<List<RoomResponse>> getAvailableRoomsByPeriodAndGuests(@RequestBody AvailableRoomRequest request) {

        List<Room> room = roomReservationService.findRoomByPeriodAndPeople(
                request.getStartDate(),
                request.getEndDate(),
                request.getAdults(),
                request.getKids());

        List<RoomResponse> rooms = room
                .stream()
                .map(room1 -> RoomConverter.convertToRoomResponse(room1))
                .collect(Collectors.toList());

        return new ResponseEntity<>(rooms, HttpStatus.OK);
    }

    public ResponseEntity<HttpStatus> deleteByRoomIdAndReservationId(@PathVariable Long id, @PathVariable Long rid) {

        try {
            roomReservationService.deleteByRoomIdAndReservationId(id, rid);

            return new ResponseEntity<>(HttpStatus.NO_CONTENT);

        } catch (Exception ex) {
            throw new RuntimeException("Reservation can not be deleted");
        }

    }


    }


