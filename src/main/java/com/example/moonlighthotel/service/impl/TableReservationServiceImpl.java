package com.example.moonlighthotel.service.impl;

import com.example.moonlighthotel.converter.TableReservationConverter;
import com.example.moonlighthotel.dto.restaurant.TableReservationRequest;
import com.example.moonlighthotel.dto.restaurant.TableReservationUpdateRequest;
import com.example.moonlighthotel.enumerations.TableZone;
import com.example.moonlighthotel.exeptions.RecordNotFoundException;
import com.example.moonlighthotel.model.table.Table;
import com.example.moonlighthotel.model.table.TableReservation;
import com.example.moonlighthotel.model.User;
import com.example.moonlighthotel.repositories.TableReservationRepository;
import com.example.moonlighthotel.service.TableReservationService;
import com.example.moonlighthotel.service.TableService;
import com.example.moonlighthotel.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;

@Service
public class TableReservationServiceImpl implements TableReservationService {

    private final TableReservationRepository tableReservationRepository;
    private final TableService tableService;
    private final UserService userService;

    @Autowired
    public TableReservationServiceImpl(TableReservationRepository tableReservationRepository, TableService tableService, UserService userService) {
        this.tableReservationRepository = tableReservationRepository;
        this.tableService = tableService;
        this.userService = userService;
    }

    @Override
    public void save(TableReservation tableReservation) {
        tableReservationRepository.save(tableReservation);
    }

    @Override
    public void deleteTableReservation(Long id, Long rid) {

        Table foundTable = tableService.findById(id);

    }

    @Override
    public List<TableReservation> getAllReservationsByTable(Long id) {

        Table foundTable = tableService.findById(id);

        return tableReservationRepository.findByTable(foundTable);
    }

    @Override
    public List<TableReservation> getTableReservationsByUser(Long id) {

        User foundUser = userService.findUserById(id);

        return tableReservationRepository.findByUser(foundUser);
    }

    @Override
    public void updateTableReservation(Long id, Long rid, TableReservationUpdateRequest request) {

        Table foundTable = tableService.findById(id);

        TableReservation foundTableReservation = findTableReservationById(rid);

        TableReservation updatedTableReservation = TableReservationConverter.update(foundTableReservation, request);

        save(updatedTableReservation);

    }

    public TableReservation findTableReservationById(Long id) {

        return tableReservationRepository.findById(id)
                .orElseThrow(() -> new RecordNotFoundException(String.format("Table reservation with id: %s, not found", id)));
    }

    @Override
    public TableReservation getReservationByIdAndTableId(Long id, Long rid) {

        Table table = tableService.findById(id);

        TableReservation tableReservation = findTableReservationById(rid);

        if (!id.equals(tableReservation.getTable().getId())) {
            throw new RuntimeException("Table id does not match");
        }

        return tableReservation;

    }

    @Override
    public List<Table> getAllAvailableTables(int people, TableZone zone, String date, String hour) {

        Instant dateToReserve = TableReservationConverter.convertRequestDateAndHourToInstant(date, hour);

        Instant start = dateToReserve.minusSeconds(3600);
        Instant end = dateToReserve.plusSeconds(3600);

        return tableReservationRepository.getAllAvailableTables(people, zone, start, end);
    }

    @Override
    public TableReservation summarizeTableReservation(Long id, TableReservationRequest request, User user) {

        Table foundTable = tableService.findById(id);

        return TableReservationConverter.convertToTableReservation(id, request, user);
    }
    }
