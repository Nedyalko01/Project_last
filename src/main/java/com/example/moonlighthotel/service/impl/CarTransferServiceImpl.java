package com.example.moonlighthotel.service.impl;

import com.example.moonlighthotel.converter.CarTransferConverter;
import com.example.moonlighthotel.dto.transfer.CarTransferRequest;
import com.example.moonlighthotel.model.User;
import com.example.moonlighthotel.model.car.Car;
import com.example.moonlighthotel.model.car.CarTransfer;
import com.example.moonlighthotel.repositories.CarTransferRepository;
import com.example.moonlighthotel.service.CarService;
import com.example.moonlighthotel.service.CarTransferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CarTransferServiceImpl implements CarTransferService {

    private final CarTransferRepository carTransferRepository;
    private final CarService carService;

    @Autowired
    public CarTransferServiceImpl(CarTransferRepository carTransferRepository, CarService carService) {
        this.carTransferRepository = carTransferRepository;
        this.carService = carService;
    }

    @Override
    public CarTransfer createCarTransfer(Long id, CarTransferRequest carTransferRequest, User user) {

        CarTransfer carTransfer = CarTransferConverter.convertToCarTransfer(id, carTransferRequest, user);
        carTransferRepository.save(carTransfer);

        return carTransfer;
    }


    @Override
    public List<CarTransfer> getAllTransfersByCarId(Long id) {
        Car foundCar = carService.findCarById(id);

        return foundCar.getCarTransfers();
    }
}
