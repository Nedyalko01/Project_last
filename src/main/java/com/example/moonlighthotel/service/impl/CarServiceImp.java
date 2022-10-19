package com.example.moonlighthotel.service.impl;

import com.example.moonlighthotel.converter.CarConverter;
import com.example.moonlighthotel.dto.transfer.CarRequest;
import com.example.moonlighthotel.exeptions.RecordNotFoundException;
import com.example.moonlighthotel.model.car.Car;
import com.example.moonlighthotel.repositories.CarRepository;
import com.example.moonlighthotel.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;

@Service
public class CarServiceImp implements CarService {

    private final CarRepository carRepository;

    @Autowired
    public CarServiceImp(CarRepository carRepository) {
        this.carRepository = carRepository;
    }


    @Override
    public void createCar(Car car) {
        carRepository.save(car);

    }

    @Override
    public void deleteCarById(Long id) {
        carRepository.deleteById(id);
    }

    @Override
    public Car findCarById(Long id) {
        return carRepository.findById(id)
                .orElseThrow(() -> new RecordNotFoundException(String.format("Car with id: %d, not found", id)));
    }

    @Override
    public Car updateCarById(Long id, CarRequest carRequest) {

        Car car = findCarById(id);

        Car updatedCar = CarConverter.update(car, carRequest);
        createCar(updatedCar);

        return updatedCar;

    }

    @Override
    public List<Car> getAvailableCars(int seats, Instant date) {
        return carRepository.findCarsBySeatsAndCarTransferDate(seats, date);
    }
}
