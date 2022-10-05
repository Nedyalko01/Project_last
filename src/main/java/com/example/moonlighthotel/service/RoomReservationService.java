package com.example.moonlighthotel.service;

import com.example.moonlighthotel.model.RoomReservation;
import com.example.moonlighthotel.model.User;

import java.util.List;

public interface  RoomReservationService {

    void save (RoomReservation roomReservation);

    List<RoomReservation> getByUserId (User user);

    List<RoomReservation> getAllRooms (User user);

    RoomReservation findById(Long id);
}
