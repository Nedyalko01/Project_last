package com.example.moonlighthotel.controller;


import com.example.moonlighthotel.converter.RoomConverter;
import com.example.moonlighthotel.dto.room.RoomRequest;
import com.example.moonlighthotel.dto.room.RoomResponse;
import com.example.moonlighthotel.exeptions.RoomNotFoundException;
import com.example.moonlighthotel.model.Room;
import com.example.moonlighthotel.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.example.moonlighthotel.constant.ExceptionConstant.ROOM_NOT_FOUND;

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


   @GetMapping(value = "/{id}")
    public ResponseEntity<RoomResponse> findById(@PathVariable Long id) {

           RoomResponse room = RoomConverter.convertToRoomResponse(roomService.findRoomById(id));

           return new ResponseEntity<>(room, HttpStatus.OK);
   }

   @DeleteMapping(value = "/{id}")
    public  ResponseEntity<String> deleteById (@PathVariable Long id) {

         try {
             roomService.deleteById(id);
             return new ResponseEntity<>(HttpStatus.NO_CONTENT);
         } catch (Exception ex) {
               throw new RoomNotFoundException(String.format(ROOM_NOT_FOUND, id));
         }
   }

   @PutMapping(value = "/{id}")
    public ResponseEntity<RoomResponse> updateRoom (@PathVariable Long id, @RequestBody RoomRequest request) {

        Room room = roomService.updateRoom(id, request);

        RoomResponse response = RoomConverter.convertToRoomResponse(room);

        return new ResponseEntity<>(response, HttpStatus.OK);

   }

}
