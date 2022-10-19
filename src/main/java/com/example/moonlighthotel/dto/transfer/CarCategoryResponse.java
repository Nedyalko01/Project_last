package com.example.moonlighthotel.dto.transfer;

import com.example.moonlighthotel.enumerations.CarTitle;

public class CarCategoryResponse {

    private Long id;
    private CarTitle title;
    private int seats;
    private Double price;

    public CarCategoryResponse() {
    }

    public CarCategoryResponse(Long id, CarTitle title, int seats, Double price) {
        this.id = id;
        this.title = title;
        this.seats = seats;
        this.price = price;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
}
