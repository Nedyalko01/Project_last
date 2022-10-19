package com.example.moonlighthotel.dto.transfer;

import com.example.moonlighthotel.enumerations.CarTitle;

public class CarCategoryRequest {

    private CarTitle title;
    private int seats;
    private Double price;
    private int position;

    public CarCategoryRequest() {
    }

    public CarCategoryRequest(CarTitle title, int seats, Double price, int position) {
        this.title = title;
        this.seats = seats;
        this.price = price;
        this.position = position;
    }

    public CarTitle getTitle() {
        return title;
    }

    public void setTitle(CarTitle title) {
        this.title = title;
    }

    public int getSeats() {
        return seats;
    }

    public void setSeats(int seats) {
        this.seats = seats;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }
}


