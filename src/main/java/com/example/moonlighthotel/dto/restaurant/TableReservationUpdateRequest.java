package com.example.moonlighthotel.dto.restaurant;

public class TableReservationUpdateRequest {

    private Long user;
    private int table;
    private String date;
    private String hour;
    private int people;
    private double price;

    public TableReservationUpdateRequest() {
    }

    public TableReservationUpdateRequest(Long user, int table, String date, String hour, int people, double price) {
        this.user = user;
        this.table = table;
        this.date = date;
        this.hour = hour;
        this.people = people;
        this.price = price;
    }

    public Long getUser() {
        return user;
    }

    public void setUser(Long user) {
        this.user = user;
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


