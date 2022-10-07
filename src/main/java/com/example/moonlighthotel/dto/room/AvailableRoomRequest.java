package com.example.moonlighthotel.dto.room;

import java.time.Instant;

public class AvailableRoomRequest {
    private Instant startDate;

    private Instant endDate;

    private int adults;

    private int kids;

    public AvailableRoomRequest() {
    }

    public AvailableRoomRequest(Instant startDate, Instant endDate, int adults, int kids) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.adults = adults;
        this.kids = kids;
    }

    public Instant getStartDate() {
        return startDate;
    }

    public void setStartDate(Instant startDate) {
        this.startDate = startDate;
    }

    public Instant getEndDate() {
        return endDate;
    }

    public void setEndDate(Instant endDate) {
        this.endDate = endDate;
    }

    public int getAdults() {
        return adults;
    }

    public void setAdults(int adults) {
        this.adults = adults;
    }

    public int getKids() {
        return kids;
    }

    public void setKids(int kids) {
        this.kids = kids;
    }
}


