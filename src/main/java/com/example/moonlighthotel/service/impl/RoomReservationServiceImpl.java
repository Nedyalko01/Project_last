package com.example.moonlighthotel.service.impl;

import com.example.moonlighthotel.exeptions.RecordNotFoundException;
import com.example.moonlighthotel.model.Room;
import com.example.moonlighthotel.model.RoomReservation;
import com.example.moonlighthotel.model.User;
import com.example.moonlighthotel.repositories.RoomReservationRepository;
import com.example.moonlighthotel.service.RoomReservationService;
import com.example.moonlighthotel.service.RoomService;
import com.example.moonlighthotel.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import java.time.Instant;
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
    public List<RoomReservation> getAll() {
        return roomReservationRepository.findAll();
    }

    @Override
    public RoomReservation findById(Long id) {
        return roomReservationRepository.findById(id)
                .orElseThrow(() -> new RecordNotFoundException(String.format("Reservation with id: %s, not found", id)));
    }


    @Override
    public List<Room> findRoomByPeriodAndPeople(Instant startDate, Instant endDate, int adults, int kids) {
        return roomReservationRepository.findRoomByPeriodAndPeople(startDate, endDate, (adults + kids));
    }




    @Override
    public void deleteByRoomIdAndReservationId(Long id, Long rid) {

        RoomReservation roomReservation = findById(id);

        Room room = roomService.findRoomById(id);

        if (!id.equals(roomReservation.getRoom().getId())) {
            throw new RuntimeException("Room id does not match to reservation");
        }

    }

    public RoomReservation findReservationByIdAndUserId (Long uid, Long rid) {

        User user = userService.findUserById(uid);
        RoomReservation roomReservation = findById(rid);

        if (!roomReservation.getUser().getId().equals(uid)) {
            throw new RuntimeException("User id - does not match to reservation");
        }
        return roomReservation;


    }
}
