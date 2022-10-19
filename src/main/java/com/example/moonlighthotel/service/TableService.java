package com.example.moonlighthotel.service;

import com.example.moonlighthotel.dto.restaurant.TableRequest;
import com.example.moonlighthotel.model.table.Table;

public interface TableService {

    void save(Table table);

    Table findById(Long id);

    void deleteTable(Long id);

    Table findByTableNumber(int number);

    void update(Long id, TableRequest request);

}
