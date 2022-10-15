package com.example.moonlighthotel.service.impl;

import com.example.moonlighthotel.model.Table;
import com.example.moonlighthotel.model.TableReservation;
import com.example.moonlighthotel.repositories.TableReservationRepository;
import com.example.moonlighthotel.service.TableReservationService;
import com.example.moonlighthotel.service.TableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TableReservationServiceImpl implements TableReservationService {

    private final TableReservationRepository tableReservationRepository;

    private final TableService tableService;

    @Autowired
    public TableReservationServiceImpl(TableReservationRepository tableReservationRepository, TableService tableService) {
        this.tableReservationRepository = tableReservationRepository;
        this.tableService = tableService;
    }

    @Override
    public void save(TableReservation tableReservation) {
        tableReservationRepository.save(tableReservation);
    }

    @Override
    public void deleteTableReservation(Long id, Long rid) {

        Table foundTable = tableService.findById(id);

    }
}
