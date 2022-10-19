package com.example.moonlighthotel.service.impl;


import com.example.moonlighthotel.converter.CarCategoryConverter;
import com.example.moonlighthotel.dto.transfer.CarCategoryRequest;
import com.example.moonlighthotel.exeptions.RecordNotFoundException;
import com.example.moonlighthotel.model.car.CarCategory;
import com.example.moonlighthotel.repositories.CarCategoryRepository;
import com.example.moonlighthotel.service.CarCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CarCategoryServiceImpl implements CarCategoryService {

    private final CarCategoryRepository carCategoryRepository;

    @Autowired
    public CarCategoryServiceImpl(CarCategoryRepository carCategoryRepository) {
        this.carCategoryRepository = carCategoryRepository;
    }


    @Override
    public void createCarCategory(CarCategory carCategory) {

        carCategoryRepository.save(carCategory);

    }

    @Override
    public CarCategory findCategoryById(Long id) {
        return carCategoryRepository.findById(id)
                .orElseThrow(() -> new RecordNotFoundException("Category not found"));
    }

    @Override
    public void deleteCarCategoryById(Long id) {
        carCategoryRepository.deleteById(id);

    }

    @Override
    public CarCategory updateCarCategory(Long id, CarCategoryRequest carCategoryRequest) {

        CarCategory foundCarCategory = findCategoryById(id);

        CarCategory updatedCarCategory = CarCategoryConverter.update(foundCarCategory, carCategoryRequest);
        createCarCategory(updatedCarCategory);

        return updatedCarCategory;
    }
}
