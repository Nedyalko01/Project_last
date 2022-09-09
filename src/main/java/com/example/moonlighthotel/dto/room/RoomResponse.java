package com.example.moonlighthotel.dto.room;

import com.example.moonlighthotel.enumerations.RoomType;
import com.example.moonlighthotel.enumerations.RoomView;

import java.util.Set;

public class RoomResponse extends RoomDto {

    private final Long id;

    private RoomResponse(Builder builder) {
        super(builder.title,
                builder.image,
                builder.images,
                builder.description,
                builder.roomView,
                builder.area,
                builder.people,
                builder.price);
        this.id = builder.id;
    }

    public Long getId() {
        return id;
    }

    public static class Builder{

        private Long id;
        private RoomType title;
        private String image;
        private Set<String> images;
        private String description;
        private RoomView roomView;
        private Integer area;
        private Integer people;
        private Double price;

        public Builder addId(Long id) {
            this.id = id;
            return this;
        }

        public Builder addTitle(RoomType title) {
            this.title = title;
            return this;
        }

        public Builder addImage(String image) {
            this.image = image;
            return this;
        }

        public Builder addImages(Set<String> images) {
            this.images = images;
            return this;
        }

        public Builder addDescription(String description) {
            this.description = description;
            return this;
        }

        public Builder addRoomView(RoomView roomView) {
            this.roomView = roomView;
            return this;
        }

        public Builder addArea(Integer area) {
            this.area = area;
            return this;
        }

        public Builder addPeople(Integer people) {
            this.people = people;
            return this;
        }

        public Builder addPrice(Double price) {
            this.price = price;
            return this;
        }

        public RoomResponse build() {
            return new RoomResponse(this);
        }
    }
}


