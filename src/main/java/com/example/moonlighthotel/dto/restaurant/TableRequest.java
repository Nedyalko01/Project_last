package com.example.moonlighthotel.dto.restaurant;

import com.example.moonlighthotel.enumerations.TableZone;

public class TableRequest {

    private TableZone zone;

    private int number;

    private int people;

    public TableRequest() {
    }

    public TableRequest(TableZone zone, int number, int people) {
        this.zone = zone;
        this.number = number;
        this.people = people;
    }

    public TableZone getZone() {
        return zone;
    }

    public void setZone(TableZone zone) {
        this.zone = zone;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public int getPeople() {
        return people;
    }

    public void setPeople(int people) {
        this.people = people;
    }
}
