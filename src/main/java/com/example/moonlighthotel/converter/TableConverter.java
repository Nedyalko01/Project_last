package com.example.moonlighthotel.converter;

import com.example.moonlighthotel.dto.restaurant.TableRequest;
import com.example.moonlighthotel.dto.restaurant.TableResponse;
import com.example.moonlighthotel.enumerations.TableZone;
import com.example.moonlighthotel.model.Table;

public class TableConverter {

    public static Table convertToTable(TableRequest tableRequest) {

        Table table = new Table();
        table.setZone(tableRequest.getZone());
        table.setNumber(table.getNumber());
        table.setPeople(tableRequest.getPeople());
        table.setSmoking(isSmoking(tableRequest.getZone()));

        return table;

    }

    public static TableResponse convertToTableResponse (Table table) {
            return new TableResponse.Builder()
                    .addId(table.getId())
                    .addZone(table.getZone())
                    .addNumber(table.getNumber())
                    .addPeople(table.getPeople())
                    .addSmoking(table.isSmoking())
                    .build();

    }

    private static boolean isSmoking(TableZone zone) {

        return zone == TableZone.TERRACE;
    }
}
