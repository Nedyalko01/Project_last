package com.example.moonlighthotel.controller;


import com.example.moonlighthotel.converter.TableConverter;
import com.example.moonlighthotel.converter.TableReservationConverter;
import com.example.moonlighthotel.dto.restaurant.*;
import com.example.moonlighthotel.enumerations.TableZone;
import com.example.moonlighthotel.exeptions.RecordNotFoundException;
import com.example.moonlighthotel.model.User;
import com.example.moonlighthotel.model.table.Table;
import com.example.moonlighthotel.model.table.TableReservation;
import com.example.moonlighthotel.service.TableReservationService;
import com.example.moonlighthotel.service.TableService;
import com.example.moonlighthotel.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/tables")
public class RestaurantController {

    private final TableService tableService;
    private final TableReservationService tableReservationService;
    private final UserService userService;


    @Autowired
    public RestaurantController(TableService tableService, TableReservationService tableReservationService, UserService userService) {
        this.tableService = tableService;
        this.tableReservationService = tableReservationService;
        this.userService = userService;
    }


    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @PostMapping
    public ResponseEntity<TableResponse> createTable(@RequestBody TableRequest request) {

        Table table = TableConverter.convertToTable(request);
        tableService.save(table);

        TableResponse tableResponse = TableConverter.convertToTableResponse(table);

        return new ResponseEntity<>(tableResponse, HttpStatus.OK);

    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<HttpStatus> deleteTableById(@PathVariable Long id) {

        try {
            tableService.deleteTable(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception ex) {
            throw new RecordNotFoundException(String.format("Table with id: %s, not found", id));
        }

    }

    @DeleteMapping(value = "/{id}/reservations/{rid}")
    public ResponseEntity<HttpStatus> deleteTableReservation(@PathVariable Long id, @PathVariable Long rid) {

        try {
            tableReservationService.deleteTableReservation(id, rid);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception ex) {
            throw new RecordNotFoundException(String.format("Table reservation with id: %d, not found", rid));
        }

    }

    @GetMapping(value = "/{id}/reservations")
    public ResponseEntity<List<TableReservationResponse>> getAllReservationsByTable(@PathVariable Long id) {

        List<TableReservation> tableReservations = tableReservationService.getAllReservationsByTable(id);

        List<TableReservationResponse> tableResponse = tableReservations
                .stream()
                .map(tableReservation -> TableReservationConverter.convertToTableReservationResponse(tableReservation))
                .collect(Collectors.toList());

        return new ResponseEntity<>(tableResponse, HttpStatus.OK);
    }

    @PutMapping(value = "/{id}/reservations/{rid}")
    public ResponseEntity<TableReservationResponse> updateTableReservations(@PathVariable Long id, @PathVariable Long rid,
                                                                            @RequestBody TableReservationUpdateRequest request) {

        tableReservationService.updateTableReservation(id, rid, request);

        TableReservation tableReservationById = tableReservationService.findTableReservationById(rid);

        TableReservationResponse response = TableReservationConverter.convertToTableReservationResponse(tableReservationById);

        return new ResponseEntity<>(response, HttpStatus.OK);

    }


    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_CLIENT')")
    @PostMapping(value = "/{id}/reservations")
    public ResponseEntity<TableReservationResponse> createTableReservation(@PathVariable Long id,
                                                                           @RequestBody TableReservationRequest request,
                                                                           @AuthenticationPrincipal User user) {
        User foundUser = userService.findUserById(user.getId());

        TableReservation tableReservation = TableReservationConverter.convertToTableReservation(id, request, foundUser);

        tableReservationService.save(tableReservation);

        TableReservationResponse response = TableReservationConverter.convertToTableReservationResponse(tableReservation);

        return new ResponseEntity<>(response, HttpStatus.OK);

    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<TableResponse> updateTable(@PathVariable Long id, @RequestBody TableRequest request) {

        tableService.update(id, request);

        Table tableById = tableService.findById(id);

        TableResponse response = TableConverter.convertToTableResponse(tableById);

        return new ResponseEntity<>(response, HttpStatus.OK);


    }

    @GetMapping(value = "/{id}/reservations/{rid}")
    public ResponseEntity<TableReservationResponse> getTableReservationByIdAndTableId(@PathVariable Long id, @PathVariable Long rid) {

        TableReservation tableReservation = tableReservationService.getReservationByIdAndTableId(id, rid);

        TableReservationResponse response = TableReservationConverter.convertToTableReservationResponse(tableReservation);

        return new ResponseEntity<>(response, HttpStatus.OK);

    }

    @GetMapping
    public ResponseEntity<List<TableResponse>> getAllAvailableTable(@RequestParam int people,
                                                                    @RequestParam TableZone zone,
                                                                    @RequestParam String date,
                                                                    @RequestParam String hour) {

       List<Table> tables = tableReservationService.getAllAvailableTables(people, zone, date, hour);

        List<TableResponse> tableResponses = tables
                .stream()
                .map(TableConverter::convertToTableResponse)
                .collect(Collectors.toList());

        return new ResponseEntity<>(tableResponses, HttpStatus.OK);
    }

    @PostMapping(value = "/{id}/summarize")
    public ResponseEntity<TableReservationResponse> summarizeTableReservation(@PathVariable Long id,
                                                                              @RequestBody TableReservationRequest request,
                                                                              @AuthenticationPrincipal User user) {

        TableReservation tableReservation = tableReservationService.summarizeTableReservation(id, request, user);

        TableReservationResponse tableReservationResponse = TableReservationConverter.convertToTableReservationResponse(tableReservation);

        return new ResponseEntity<>(tableReservationResponse, HttpStatus.OK);
    }


}
