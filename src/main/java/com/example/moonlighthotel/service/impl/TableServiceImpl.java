package com.example.moonlighthotel.service.impl;

import com.example.moonlighthotel.converter.TableConverter;
import com.example.moonlighthotel.dto.restaurant.TableRequest;
import com.example.moonlighthotel.exeptions.RecordNotFoundException;
import com.example.moonlighthotel.model.table.Table;
import com.example.moonlighthotel.repositories.TableRepository;
import com.example.moonlighthotel.service.TableService;
import org.springframework.stereotype.Service;

@Service
public class TableServiceImpl implements TableService {

    private final TableRepository tableRepository;

    public TableServiceImpl(TableRepository tableRepository) {
        this.tableRepository = tableRepository;
    }


    @Override
    public void save(Table table) {
        tableRepository.save(table);

    }

    @Override
    public Table findById(Long id) {
        return tableRepository.findById(id)
                .orElseThrow(() -> new RecordNotFoundException(String.format("Table with id: %d, not found", id)));
    }

    public void deleteTable(Long id) {
        tableRepository.deleteById(id);
    }

    @Override
    public Table findByTableNumber(int number) {
        return tableRepository.findByNumber(number)
                .orElseThrow(() -> new RecordNotFoundException(String.format("Table with number: %d, not found", number)));
    }

    @Override
    public void update(Long id, TableRequest request) {

        Table table = findById(id);

        save(TableConverter.update(table, request));

    }
}
