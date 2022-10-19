package com.example.moonlighthotel.controller;


import com.example.moonlighthotel.converter.CarCategoryConverter;
import com.example.moonlighthotel.converter.CarConverter;
import com.example.moonlighthotel.converter.CarTransferConverter;
import com.example.moonlighthotel.dto.transfer.*;
import com.example.moonlighthotel.exeptions.RecordNotFoundException;
import com.example.moonlighthotel.model.User;
import com.example.moonlighthotel.model.car.Car;
import com.example.moonlighthotel.model.car.CarCategory;
import com.example.moonlighthotel.model.car.CarTransfer;
import com.example.moonlighthotel.service.CarCategoryService;
import com.example.moonlighthotel.service.CarService;
import com.example.moonlighthotel.service.CarTransferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping(value = "cars", produces = MediaType.APPLICATION_JSON_VALUE)
public class TransferController {

    private final CarService carService;
    private final CarCategoryService carCategoryService;
    private final CarTransferService carTransferService;

    @Autowired
    public TransferController(CarService carService, CarCategoryService carCategoryService, CarTransferService carTransferService) {
        this.carService = carService;
        this.carCategoryService = carCategoryService;
        this.carTransferService = carTransferService;
    }


    @PostMapping(value = "categories")
    public ResponseEntity<CarCategoryResponse> createCarCategory(@RequestBody CarCategoryRequest request) {

        CarCategory carCategory = CarCategoryConverter.convertToCarCategory(request);
        carCategoryService.createCarCategory(carCategory);

        CarCategoryResponse response = CarCategoryConverter.convertToCarCategoryResponse(carCategory);
        return new ResponseEntity<>(response, HttpStatus.CREATED);


    }
    @PostMapping
    public ResponseEntity<CarResponse> createCar(@RequestBody CarRequest request) {

        Car car = CarConverter.convertToCar(request);
        carService.createCar(car);

        CarResponse carResponse = CarConverter.convertToCarResponse(car);

        return new ResponseEntity<>(carResponse, HttpStatus.OK);


    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<HttpStatus> deleteCar(@PathVariable Long id) {

        try {
            carService.deleteCarById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception ex) {
            throw new RecordNotFoundException(String.format("Car with id: %d, not found", id));
        }
    }

    @DeleteMapping(value = "/categories/{id}")
    public ResponseEntity<HttpStatus> deleteCarCategory(@PathVariable Long id) {

        try {
            carCategoryService.deleteCarCategoryById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {

            throw new RecordNotFoundException(String.format("Car category with id: %d, not found", id));
        }
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<CarResponse> updateCar(@PathVariable Long id, @RequestBody CarRequest carRequest) {

        Car updatedCar = carService.updateCarById(id, carRequest);

        CarResponse carResponse =  CarConverter.convertToCarResponse(updatedCar);

        return new ResponseEntity<>(carResponse, HttpStatus.OK);

    }

    @PutMapping(value = "/categories/{id}")
    public ResponseEntity<CarCategoryResponse> updateCarCategory(@PathVariable Long id,
                                                                 @RequestBody CarCategoryRequest carCategoryRequest) {

        CarCategory updatedCarCategory = carCategoryService.updateCarCategory(id, carCategoryRequest);

        CarCategoryResponse carCategoryResponse = CarCategoryConverter.convertToCarCategoryResponse(updatedCarCategory);

        return new ResponseEntity<>(carCategoryResponse, HttpStatus.OK);
    }
    @PostMapping(value = "/{id}/transfers")
    public ResponseEntity<CarTransferResponse> createCarTransfer(@PathVariable Long id, @RequestBody CarTransferRequest carTransferRequest,
                                                                 @AuthenticationPrincipal User user) {

        CarTransfer carTransfer = carTransferService.createCarTransfer(id, carTransferRequest, user);
        CarTransferResponse carTransferResponse = CarTransferConverter.convertToCarTransferResponse(carTransfer);

        return new ResponseEntity<>(carTransferResponse, HttpStatus.CREATED);
    }

    @GetMapping(value = "/{id}/transfers")
    public ResponseEntity<List<CarTransferResponse>> getAllTransfersByCarId(@PathVariable Long id) {

        List<CarTransfer> carTransfers = carTransferService.getAllTransfersByCarId(id);

        List<CarTransferResponse> carTransferResponses = carTransfers
                .stream()
                .map(CarTransferConverter::convertToCarTransferResponse)
                .collect(Collectors.toList());

        return new ResponseEntity<>(carTransferResponses, HttpStatus.OK);
    }

    @GetMapping(value = "/available")
    public ResponseEntity<List<CarResponse>> getAvailableCars(@RequestParam Instant date,
                                                              @RequestParam int seats) {

        List<Car> getAvailableCarsBySeatsAndCarTransferDate = carService.getAvailableCars(seats, date);

        List<CarResponse> carResponses = getAvailableCarsBySeatsAndCarTransferDate
                .stream()
                .map(CarConverter::convertToCarResponse)
                .collect(Collectors.toList());

        return new ResponseEntity<>(carResponses, HttpStatus.OK);
    }

}