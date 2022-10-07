package com.example.moonlighthotel.converter;

import com.example.moonlighthotel.dto.room.RoomResponse;
import com.example.moonlighthotel.dto.roomreservation.RoomReservationRequest;
import com.example.moonlighthotel.dto.roomreservation.RoomReservationResponse;
import com.example.moonlighthotel.model.Room;
import com.example.moonlighthotel.model.RoomReservation;
import com.example.moonlighthotel.model.User;
import com.example.moonlighthotel.service.impl.RoomServiceImpl;
import com.example.moonlighthotel.service.impl.UserServiceImpl;

import java.time.Duration;
import java.time.Instant;

public class RoomReservationConverter {

    private static UserServiceImpl userService;

    private static RoomServiceImpl roomService;

    public RoomReservationConverter(UserServiceImpl userService, RoomServiceImpl roomService) {
        RoomReservationConverter.userService = userService;
        RoomReservationConverter.roomService = roomService;

    }

    public static RoomReservation convertToRoomReservation(Long id, RoomReservationRequest request) {

        User user = userService.findUserById(id);
        Room room = roomService.findRoomById(request.getUser());

        Instant startDate = Instant.parse(request.getStart_date());
        Instant endDate = Instant.parse(request.getEnd_date());

        Double totalPrice = calculateDays(startDate, endDate) * room.getPrice();

        RoomReservation roomReservation = new RoomReservation();

        roomReservation.setCreatedAt(Instant.now());
        roomReservation.setCheckIn(startDate);
        roomReservation.setCheckOut(endDate);
        roomReservation.setAdults(request.getAdults());
        roomReservation.setKids(request.getKids());
        roomReservation.setFacilities(request.getType_bed());
        roomReservation.setAdults(roomReservation.getAdults());
        roomReservation.setKids(roomReservation.getKids());
        roomReservation.setUser(user);
        roomReservation.setFacilities(request.getType_bed());
        roomReservation.setTotalPrice(totalPrice);

        roomReservation.setRoom(room);

        return roomReservation;

    }

    public static RoomReservationResponse convertToRoomReservationResponse(Long id, RoomReservation roomReservation) {

        Room room = roomService.findRoomById(id);

        RoomResponse roomResponse = RoomConverter.convertToRoomResponse(room);

        Double totalPrice = calculateDays(roomReservation.getCheckIn(), roomReservation.getCheckOut()) * room.getPrice();

        int daysPeriod = calculateDays(roomReservation.getCheckIn(), roomReservation.getCheckOut());

        RoomReservationResponse roomReservationResponse = new RoomReservationResponse();

        roomReservationResponse.setId(roomReservation.getId());
        roomReservationResponse.setStart_date(roomReservation.getCheckIn().toString());
        roomReservationResponse.setEnd_date(roomReservation.getCheckOut().toString());
        roomReservationResponse.setAdults(roomReservation.getAdults());
        roomReservationResponse.setKids(roomReservation.getKids());
        roomReservationResponse.setPrice(totalPrice);
        roomReservationResponse.setRoom(roomResponse);

        return roomReservationResponse;

    }



    private static Integer calculateDays(Instant startDate, Instant endDate) {

        Long duration = Duration.between(startDate, endDate).toDays();

        return duration.intValue();
    }
}
