package com.example.moonlighthotel.service;

import com.example.moonlighthotel.dto.transfer.CarRequest;
import com.example.moonlighthotel.model.car.Car;

import java.time.Instant;
import java.util.List;

public interface CarService {

    void createCar(Car car);

    void deleteCarById(Long id);

    Car findCarById(Long id);

    Car updateCarById(Long id, CarRequest carRequest);

    List<Car> getAvailableCars(int seats, Instant date);
}
