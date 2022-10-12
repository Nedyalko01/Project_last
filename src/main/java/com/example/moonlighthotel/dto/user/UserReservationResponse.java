package com.example.moonlighthotel.dto.user;

import com.example.moonlighthotel.dto.room.RoomResponse;
import com.example.moonlighthotel.enumerations.BedType;
import com.example.moonlighthotel.enumerations.RoomView;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

public class UserReservationResponse {
    private Long id;

    private Integer adults;

    private Integer kids;

    private String start_date;

    private String end_date;

    private Integer days;

    @Enumerated(EnumType.STRING)
    private BedType type_bed;

    @Enumerated(EnumType.STRING)
    private RoomView view;

    private Double price;

    private String date;

    private String status;

    private RoomResponse room;
    private UserResponse user;

    public UserReservationResponse() {
    }

    public UserReservationResponse(Long id, Integer adults, Integer kids, String start_date, String end_date,
                                   Integer days, BedType type_bed, RoomView view, Double price, String date,
                                   String status, RoomResponse room, UserResponse user) {
        this.id = id;
        this.adults = adults;
        this.kids = kids;
        this.start_date = start_date;
        this.end_date = end_date;
        this.days = days;
        this.type_bed = type_bed;
        this.view = view;
        this.price = price;
        this.date = date;
        this.status = status;
        this.room = room;
        this.user = user;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getAdults() {
        return adults;
    }

    public void setAdults(Integer adults) {
        this.adults = adults;
    }

    public Integer getKids() {
        return kids;
    }

    public void setKids(Integer kids) {
        this.kids = kids;
    }

    public String getStart_date() {
        return start_date;
    }

    public void setStart_date(String start_date) {
        this.start_date = start_date;
    }

    public String getEnd_date() {
        return end_date;
    }

    public void setEnd_date(String end_date) {
        this.end_date = end_date;
    }

    public Integer getDays() {
        return days;
    }

    public void setDays(Integer days) {
        this.days = days;
    }

    public BedType getType_bed() {
        return type_bed;
    }

    public void setType_bed(BedType type_bed) {
        this.type_bed = type_bed;
    }

    public RoomView getView() {
        return view;
    }

    public void setView(RoomView view) {
        this.view = view;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public RoomResponse getRoom() {
        return room;
    }

    public void setRoom(RoomResponse room) {
        this.room = room;
    }

    public UserResponse getUser() {
        return user;
    }

    public void setUser(UserResponse user) {
        this.user = user;
    }

}
