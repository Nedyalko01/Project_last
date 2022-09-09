package com.example.moonlighthotel.service.impl;

import com.example.moonlighthotel.converter.RoomConverter;
import com.example.moonlighthotel.dto.room.RoomRequest;
import com.example.moonlighthotel.dto.room.RoomResponse;
import com.example.moonlighthotel.exeptions.RoomNotFoundException;
import com.example.moonlighthotel.model.Room;
import com.example.moonlighthotel.repositories.RoomRepository;
import com.example.moonlighthotel.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import static com.example.moonlighthotel.constant.ExceptionConstant.ROOM_NOT_FOUND;


@Service
public class RoomServiceImpl implements RoomService {

    private final RoomRepository roomRepository;

    private final RoomConverter roomConverter;

    @Autowired
    public RoomServiceImpl(RoomRepository roomRepository, RoomConverter roomConverter) {
        this.roomRepository = roomRepository;
        this.roomConverter = roomConverter;
    }


    @Override
    public Room findRoomById(Long id) {
        return roomRepository.findById(id)
                .orElseThrow(() -> new RoomNotFoundException(String.format(ROOM_NOT_FOUND, id)));
    }

    @Override
    public void save(Room room) {
        roomRepository.save(room);

    }

    @Override
    public Set<RoomResponse> getAllRooms() {

        Set<Room> rooms = new HashSet<>(roomRepository.findAll());

        return rooms
                .stream()
                .map(room -> roomConverter.convertToRoomResponse(room))
                .collect(Collectors.toSet());


    }

    @Override
    public Room updateRoom(Long id, RoomRequest roomRequest) {

        Room room = findRoomById(id);

        Room updatedRoom = RoomConverter.update(room, roomRequest);

        roomRepository.save(updatedRoom);

        return updatedRoom;
    }

    @Override
    public void deleteById(Long id) {


        roomRepository.deleteById(id);

    }
}
