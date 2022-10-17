package com.example.moonlighthotel.dto.restaurant;

public class TableReservationRequest {

    private int table;
    private String date;
    private String hour;
    private int people;
    private double price;

    public TableReservationRequest() {
    }

    public TableReservationRequest(int table, String date, String hour, int people, double price) {
        this.table = table;
        this.date = date;
        this.hour = hour;
        this.people = people;
        this.price = price;
    }

    public int getTable() {
        return table;
    }

    public void setTable(int table) {
        this.table = table;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getHour() {
        return hour;
    }

    public void setHour(String hour) {
        this.hour = hour;
    }

    public int getPeople() {
        return people;
    }

    public void setPeople(int people) {
        this.people = people;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}


