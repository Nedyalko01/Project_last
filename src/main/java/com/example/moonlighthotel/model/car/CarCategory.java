package com.example.moonlighthotel.model.car;

import com.example.moonlighthotel.enumerations.CarTitle;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "categories")
public class CarCategory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private CarTitle title;

    private int seats;

    private Double price;

    @OneToMany(mappedBy = "category")
    private List<Car> car;

    public CarCategory() {
    }

    public CarCategory(Long id, CarTitle title, int seats, Double price, List<Car> car) {
        this.id = id;
        this.title = title;
        this.seats = seats;
        this.price = price;
        this.car = car;
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

    public List<Car> getCar() {
        return car;
    }

    public void setCar(List<Car> car) {
        this.car = car;
    }
}

