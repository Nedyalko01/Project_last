package com.example.moonlighthotel.dto.transfer;

import com.example.moonlighthotel.dto.user.UserResponse;

public class CarTransferResponse {
    private Long id;
    private Double price;
    private String date;
    private String created;
    private CarResponse car;
    private UserResponse user;

    public CarTransferResponse() {
    }

    public CarTransferResponse(Long id, Double price, String date, String created, CarResponse car,
                               UserResponse user) {
        this.id = id;
        this.price = price;
        this.date = date;
        this.created = created;
        this.car = car;
        this.user = user;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getCreated() {
        return created;
    }

    public void setCreated(String created) {
        this.created = created;
    }

    public CarResponse getCar() {
        return car;
    }

    public void setCar(CarResponse car) {
        this.car = car;
    }

    public UserResponse getUser() {
        return user;
    }

    public void setUser(UserResponse user) {
        this.user = user;
    }
}
