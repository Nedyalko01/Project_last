package com.example.moonlighthotel.dto.room;

import com.example.moonlighthotel.enumerations.RoomType;
import com.example.moonlighthotel.enumerations.RoomView;

import java.util.Set;

public class RoomDto {
    private RoomType title;
    private String image;
    private Set<String> images;
    private String description;
    private RoomView roomView;
    private Integer area;
    private Integer people;
    private Double price;

    public RoomDto(RoomType title, String image, Set<String> images, String description,
                   RoomView roomView, Integer area, Integer people, Double price) {
        this.title = title;
        this.image = image;
        this.images = images;
        this.description = description;
        this.roomView = roomView;
        this.area = area;
        this.people = people;
        this.price = price;
    }

    public RoomType getTitle() {
        return title;
    }

    public void setTitle(RoomType title) {
        this.title = title;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Set<String> getImages() {
        return images;
    }

    public void setImages(Set<String> images) {
        this.images = images;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public RoomView getRoomView() {
        return roomView;
    }

    public void setRoomView(RoomView roomView) {
        this.roomView = roomView;
    }

    public Integer getArea() {
        return area;
    }

    public void setArea(Integer area) {
        this.area = area;
    }

    public Integer getPeople() {
        return people;
    }

    public void setPeople(Integer people) {
        this.people = people;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
}
