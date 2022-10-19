package com.example.moonlighthotel.service;

import com.example.moonlighthotel.dto.transfer.CarTransferRequest;
import com.example.moonlighthotel.model.User;
import com.example.moonlighthotel.model.car.CarTransfer;

import java.util.List;

public interface CarTransferService {

    CarTransfer createCarTransfer(Long id, CarTransferRequest carTransferRequest, User user);

    List<CarTransfer> getAllTransfersByCarId(Long id);
}
