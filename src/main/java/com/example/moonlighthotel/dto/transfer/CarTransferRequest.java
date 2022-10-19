package com.example.moonlighthotel.dto.transfer;

public class CarTransferRequest {

    private String date;
    private int seats;
    private int model;

    public CarTransferRequest() {
    }

    public CarTransferRequest(String date, int seats, int model) {
        this.date = date;
        this.seats = seats;
        this.model = model;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getSeats() {
        return seats;
    }

    public void setSeats(int seats) {
        this.seats = seats;
    }

    public int getModel() {
        return model;
    }

    public void setModel(int model) {
        this.model = model;
    }
}
