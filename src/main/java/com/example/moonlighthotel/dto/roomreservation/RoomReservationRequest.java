package com.example.moonlighthotel.dto.roomreservation;

import com.example.moonlighthotel.enumerations.BedType;
import com.example.moonlighthotel.enumerations.RoomView;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

public class RoomReservationRequest {

    private Long user;

    private String start_date;

    private String end_date;

    private Integer adults;

    private Integer kids;

    @Enumerated(EnumType.STRING)
    private BedType type_bed;

    @Enumerated(EnumType.STRING)
    private RoomView view;

    public RoomReservationRequest(Long user, String start_date, String end_date,
                                  Integer adults, Integer kids, BedType type_bed,
                                  RoomView view) {
        this.user = user;
        this.start_date = start_date;
        this.end_date = end_date;
        this.adults = adults;
        this.kids = kids;
        this.type_bed = type_bed;
        this.view = view;
    }

    public Long getUser() {
        return user;
    }

    public void setUser(Long user) {
        this.user = user;
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
}
