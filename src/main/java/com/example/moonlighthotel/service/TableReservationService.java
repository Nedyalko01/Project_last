package com.example.moonlighthotel.service;

import com.example.moonlighthotel.model.TableReservation;

public interface TableReservationService {

    void save(TableReservation tableReservation);

    void deleteTableReservation(Long id, Long rid);
}
