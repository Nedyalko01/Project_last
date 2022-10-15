package com.example.moonlighthotel.service.impl;

import com.example.moonlighthotel.exeptions.RecordNotFoundException;
import com.example.moonlighthotel.model.Table;
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
}
