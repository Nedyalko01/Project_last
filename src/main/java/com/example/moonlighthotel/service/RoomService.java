package com.example.moonlighthotel.service;


import com.example.moonlighthotel.dto.room.RoomRequest;
import com.example.moonlighthotel.dto.room.RoomResponse;
import com.example.moonlighthotel.model.Room;

import java.util.Set;

public interface RoomService {

    Room findRoomById(Long id);

    void save(Room room);

    Set<RoomResponse> getAllRooms();

    Room updateRoom(Long id, RoomRequest roomRequest);

    void deleteById(Long id);

}
