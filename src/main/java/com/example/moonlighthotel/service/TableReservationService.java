package com.example.moonlighthotel.service;

import com.example.moonlighthotel.dto.restaurant.TableReservationRequest;
import com.example.moonlighthotel.dto.restaurant.TableReservationUpdateRequest;
import com.example.moonlighthotel.enumerations.TableZone;
import com.example.moonlighthotel.model.table.Table;
import com.example.moonlighthotel.model.table.TableReservation;
import com.example.moonlighthotel.model.User;

import java.util.List;

public interface TableReservationService {

    void save(TableReservation tableReservation);

    void deleteTableReservation(Long id, Long rid);

    List<TableReservation> getAllReservationsByTable(Long id);

    List<TableReservation> getTableReservationsByUser(Long id);

    void updateTableReservation(Long id, Long rid, TableReservationUpdateRequest request);

    TableReservation findTableReservationById(Long id);

    TableReservation getReservationByIdAndTableId(Long id, Long rid);

    List<Table> getAllAvailableTables(int people, TableZone zone, String date, String hour);

    TableReservation summarizeTableReservation(Long id, TableReservationRequest request, User user);


}
