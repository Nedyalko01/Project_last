package com.example.moonlighthotel.dto.room;

import com.example.moonlighthotel.enumerations.RoomType;
import com.example.moonlighthotel.enumerations.RoomView;

import java.util.Set;

public class RoomRequest extends RoomDto {

    private Integer count;

    public RoomRequest(RoomType title, String image,
                       Set<String> images, String description, RoomView roomView,
                       Integer area, Integer people, Double price, Integer count) {
        super(title, image, images, description, roomView, area, people, price);

        this.count = count;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;

    }
}
