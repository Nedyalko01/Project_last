package com.example.moonlighthotel.runners;

import com.example.moonlighthotel.enumerations.RoomType;
import com.example.moonlighthotel.enumerations.RoomView;
import com.example.moonlighthotel.model.Room;
import com.example.moonlighthotel.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.HashSet;


@Component
public class RoomRunner implements CommandLineRunner {

    private final RoomService roomService;

    @Autowired
    public RoomRunner(RoomService roomService) {
        this.roomService = roomService;
    }

    @Override
    public void run(String... args) throws Exception {

        Room standardSwimmingPoolViewRoom = new Room();

        standardSwimmingPoolViewRoom.setTitle(RoomType.STANDARD);
        standardSwimmingPoolViewRoom.setImage("some_picture.jpg");
        standardSwimmingPoolViewRoom.setImages(new HashSet<>());
        standardSwimmingPoolViewRoom.setDescription("Standard room with swimming pool view");
        standardSwimmingPoolViewRoom.setArea(24);
        standardSwimmingPoolViewRoom.setRoomView(RoomView.SWIMMING_POOL);
        standardSwimmingPoolViewRoom.setPeople(2);
        standardSwimmingPoolViewRoom.setPrice(220.0);
        standardSwimmingPoolViewRoom.setCount(2);

        roomService.save(standardSwimmingPoolViewRoom);

    }
}
