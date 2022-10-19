package com.example.moonlighthotel.converter;

import com.example.moonlighthotel.dto.transfer.CarResponse;
import com.example.moonlighthotel.dto.transfer.CarTransferRequest;
import com.example.moonlighthotel.dto.transfer.CarTransferResponse;
import com.example.moonlighthotel.dto.user.UserResponse;
import com.example.moonlighthotel.model.User;
import com.example.moonlighthotel.model.car.Car;
import com.example.moonlighthotel.model.car.CarTransfer;
import com.example.moonlighthotel.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

@Component
public class CarTransferConverter {

    private static CarService carService;

    @Autowired
    public CarTransferConverter(CarService carService) {
        CarTransferConverter.carService = carService;
    }
    public static CarTransfer convertToCarTransfer(Long id, CarTransferRequest carTransferRequest, User user) {

        Car car = carService.findCarById(id);

        CarTransfer carTransfer = new CarTransfer();
        carTransfer.setPrice(car.getCategory().getPrice());
        carTransfer.setDate(convertTransferDateToInstant(carTransferRequest.getDate()));
        carTransfer.setCreated(Instant.now().toString());
        carTransfer.setCar(car);
        carTransfer.setUser(user);

        return carTransfer;
    }

    public static CarTransferResponse convertToCarTransferResponse(CarTransfer carTransfer) {

        CarResponse carResponse = CarConverter.convertToCarResponse(carTransfer.getCar());

        UserResponse userResponse = UserConverter.convertToUserDto(carTransfer.getUser());

        CarTransferResponse carTransferResponse = new CarTransferResponse();
        carTransferResponse.setId(carTransfer.getId());
        carTransferResponse.setPrice(carTransfer.getPrice());
        carTransferResponse.setDate(carTransfer.getDate().toString());
        carTransferResponse.setCreated(carTransfer.getCreated());
        carTransferResponse.setCar(carResponse);
        carTransferResponse.setUser(userResponse);

        return carTransferResponse;
    }

    private static Instant convertTransferDateToInstant(String transferDate) {

        String concatenatedDate = transferDate + " " + "00:00:00";

        return LocalDateTime
                .parse(concatenatedDate, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"))
                .atZone(ZoneId.systemDefault())
                .toInstant();
    }
}
