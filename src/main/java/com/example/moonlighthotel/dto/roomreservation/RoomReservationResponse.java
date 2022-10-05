package com.example.moonlighthotel.dto.roomreservation;

import com.example.moonlighthotel.dto.room.RoomResponse;

public class RoomReservationResponse {

    private Long id;

    private String start_date;

    private String end_date;

    private Integer days;

    private Integer adults;

    private Integer kids;

    private Double price;

    private RoomResponse room;

    public RoomReservationResponse() {
    }

    public RoomReservationResponse(Long id, String start_date, String end_date,
                                   Integer days, Integer adults, Integer kids,
                                   Double price, RoomResponse room) {
        this.id = id;
        this.start_date = start_date;
        this.end_date = end_date;
        this.days = days;
        this.adults = adults;
        this.kids = kids;
        this.price = price;
        this.room = room;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public RoomResponse getRoom() {
        return room;
    }

    public void setRoom(RoomResponse room) {
        this.room = room;
    }
}
