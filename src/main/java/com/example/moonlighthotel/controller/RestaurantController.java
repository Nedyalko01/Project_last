package com.example.moonlighthotel.controller;


import com.example.moonlighthotel.converter.TableConverter;
import com.example.moonlighthotel.dto.restaurant.TableRequest;
import com.example.moonlighthotel.dto.restaurant.TableResponse;
import com.example.moonlighthotel.exeptions.RecordNotFoundException;
import com.example.moonlighthotel.model.Table;
import com.example.moonlighthotel.service.TableReservationService;
import com.example.moonlighthotel.service.TableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/tables")
public class RestaurantController {

    private final TableService tableService;
    private final TableReservationService tableReservationService;


    @Autowired
    public RestaurantController(TableService tableService, TableReservationService tableReservationService) {
        this.tableService = tableService;
        this.tableReservationService = tableReservationService;
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

        try{
           tableReservationService.deleteTableReservation(id, rid);
           return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception ex) {
            throw new RecordNotFoundException(String.format("Table reservation with id: %d, not found", rid));
        }
    }
}
