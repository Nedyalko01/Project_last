package com.example.moonlighthotel.controller;


import com.example.moonlighthotel.converter.RoomConverter;
import com.example.moonlighthotel.dto.room.RoomRequest;
import com.example.moonlighthotel.dto.room.RoomResponse;
import com.example.moonlighthotel.model.Room;
import com.example.moonlighthotel.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/room")
public class RoomController {

 private final RoomService roomService;

    @Autowired
    public RoomController(RoomService roomService) {
        this.roomService = roomService;
    }

   @PostMapping
    public ResponseEntity<RoomResponse> createRoom (@RequestBody RoomRequest request) {

        Room room = RoomConverter.convertToRoom(request);

        roomService.save(room);

        RoomResponse roomResponse = RoomConverter.convertToRoomResponse(room);

        return new ResponseEntity<>(roomResponse, HttpStatus.CREATED);

   }
}
