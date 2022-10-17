package com.example.moonlighthotel.service;

import com.example.moonlighthotel.model.Table;

public interface TableService {

    void save(Table table);

    Table findById(Long id);

    void deleteTable(Long id);

    Table findByTableNumber(int number);

}
