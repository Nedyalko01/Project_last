package com.example.moonlighthotel.service;

import com.example.moonlighthotel.model.TableReservation;

import java.util.List;

public interface TableReservationService {

    void save(TableReservation tableReservation);

    void deleteTableReservation(Long id, Long rid);

    List<TableReservation> getAllReservationsByTable(Long id);

    List<TableReservation> getTableReservationsByUser(Long id);
}
