package com.example.moonlighthotel.service.impl;

import com.example.moonlighthotel.exeptions.RecordNotFoundException;
import com.example.moonlighthotel.model.RoomReservation;
import com.example.moonlighthotel.model.User;
import com.example.moonlighthotel.repositories.RoomReservationRepository;
import com.example.moonlighthotel.service.RoomReservationService;
import com.example.moonlighthotel.service.RoomService;
import com.example.moonlighthotel.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoomReservationServiceImpl implements RoomReservationService {

    private final RoomReservationRepository roomReservationRepository;

    private final UserService userService;

    private final RoomService roomService;


    @Autowired
    public RoomReservationServiceImpl(RoomReservationRepository roomReservationRepository, UserService userService, RoomService roomService) {
        this.roomReservationRepository = roomReservationRepository;
        this.userService = userService;
        this.roomService = roomService;
    }

    @Override
    public void save(RoomReservation roomReservation) {

       roomReservationRepository.save(roomReservation);
    }

    @Override
    public List<RoomReservation> getByUserId(User user) {

        RoomReservation roomReservation = new RoomReservation();
        roomReservation.setUser(user);

        return roomReservationRepository.findAll(Example.of(roomReservation));
    }

    @Override
    public List<RoomReservation> getAllRooms(User user) {
        return roomReservationRepository.findAll();
    }

    @Override
    public RoomReservation findById(Long id) {
        return roomReservationRepository.findById(id)
                .orElseThrow(() -> new RecordNotFoundException(String.format("Reservation with id: %s, not found", id)));
    }
}
