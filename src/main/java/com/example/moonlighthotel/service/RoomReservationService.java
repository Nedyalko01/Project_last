package com.example.moonlighthotel.service;

import com.example.moonlighthotel.model.Room;
import com.example.moonlighthotel.model.RoomReservation;
import com.example.moonlighthotel.model.User;

import java.time.Instant;
import java.util.List;

public interface  RoomReservationService {

    void save (RoomReservation roomReservation);

    List<RoomReservation> getByUserId (User user);

    List<RoomReservation> getAll ();

    RoomReservation findById(Long id);

    List<Room> findRoomByPeriodAndPeople(Instant startDate, Instant endDate, int adults, int kids);

    void deleteByRoomIdAndReservationId(Long id, Long rid);

    RoomReservation findReservationByIdAndUserId(Long uid, Long rid);
}
