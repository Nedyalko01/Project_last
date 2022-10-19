package com.example.moonlighthotel.service;

import com.example.moonlighthotel.dto.transfer.CarCategoryRequest;
import com.example.moonlighthotel.model.car.CarCategory;

public interface CarCategoryService {

    void createCarCategory(CarCategory carCategory);

    CarCategory findCategoryById(Long id);

    void deleteCarCategoryById(Long id);

    CarCategory updateCarCategory(Long id, CarCategoryRequest carCategoryRequest);
}
