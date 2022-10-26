package com.example.moonlighthotel.converter;

import com.example.moonlighthotel.dto.room.RoomResponse;
import com.example.moonlighthotel.dto.roomreservation.RoomReservationRequest;
import com.example.moonlighthotel.dto.roomreservation.RoomReservationResponse;
import com.example.moonlighthotel.dto.user.UserReservationResponse;
import com.example.moonlighthotel.dto.user.UserResponse;
import com.example.moonlighthotel.model.Room;
import com.example.moonlighthotel.model.RoomReservation;
import com.example.moonlighthotel.model.User;
import com.example.moonlighthotel.service.impl.RoomServiceImpl;
import com.example.moonlighthotel.service.impl.UserServiceImpl;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.Instant;

@Component
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
        roomReservationResponse.setDays(daysPeriod);
        roomReservationResponse.setAdults(roomReservation.getAdults());
        roomReservationResponse.setKids(roomReservation.getKids());
        roomReservationResponse.setPrice(totalPrice);
        roomReservationResponse.setRoom(roomResponse);

        return roomReservationResponse;


    }

    public static UserReservationResponse convertToUserReservationResponse(RoomReservation roomReservation) {

        User user = roomReservation.getUser();
        UserResponse userResponse = UserConverter.convertToUserResponse(user);

        Room room = roomReservation.getRoom();
        RoomResponse roomResponse = RoomConverter.convertToRoomResponse(room);

        int daysPeriod = calculateDays(roomReservation.getCheckIn(), roomReservation.getCheckOut());
        Double totalPrice = daysPeriod * room.getPrice();

        UserReservationResponse userReservationResponse = new UserReservationResponse();

        userReservationResponse.setId(roomReservation.getId());
        userReservationResponse.setAdults(roomReservation.getAdults());
        userReservationResponse.setKids(roomReservation.getKids());
        userReservationResponse.setStart_date(roomReservation.getCheckIn().toString());
        userReservationResponse.setEnd_date(roomReservation.getCheckOut().toString());
        userReservationResponse.setDays(daysPeriod);
        userReservationResponse.setType_bed(roomReservation.getFacilities());
        userReservationResponse.setView(room.getRoomView());
        userReservationResponse.setPrice(totalPrice);
        userReservationResponse.setDate(roomReservation.getCreatedAt().toString());
        userReservationResponse.setStatus("paid");
        userReservationResponse.setRoom(roomResponse);
        userReservationResponse.setUser(userResponse);

        return userReservationResponse;

    }


    private static Integer calculateDays(Instant startDate, Instant endDate) {

        Long duration = Duration.between(startDate, endDate).toDays();

        return duration.intValue();
    }
}
