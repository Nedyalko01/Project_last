package com.example.moonlighthotel.dto.restaurant;

import com.example.moonlighthotel.dto.user.UserResponse;

import java.time.Instant;

public class TableReservationResponse {

    private Long id;
    private Instant date;
    private int people;
    private double price;
    private String updated;
    private TableResponse table;
    private UserResponse user;

    private TableReservationResponse(Builder builder) {
        this.id = builder.id;
        this.date = builder.date;
        this.people = builder.people;
        this.price = builder.price;
        this.updated = builder.updated;
        this.table = builder.table;
        this.user = builder.user;
    }

    public Long getId() {
        return id;
    }

    public Instant getDate() {
        return date;
    }

    public int getPeople() {
        return people;
    }

    public double getPrice() {
        return price;
    }

    public String getUpdated() {
        return updated;
    }

    public TableResponse getTable() {
        return table;
    }

    public UserResponse getUser() {
        return user;
    }

    public static class Builder {

        private Long id;
        private Instant date;
        private int people;
        private double price;
        private String updated;
        private TableResponse table;
        private UserResponse user;

        public Builder addId(Long id) {
            this.id = id;
            return this;
        }

        public Builder addDate(Instant date) {
            this.date = date;
            return this;
        }

        public Builder addPeople(int people) {
            this.people = people;
            return this;
        }

        public Builder addPrice(double price) {
            this.price = price;
            return this;
        }

        public Builder addUpdated(String updated) {
            this.updated = updated;
            return this;
        }

        public  Builder addTable(TableResponse table) {
            this.table = table;
            return this;
        }

        public Builder addUser(UserResponse user) {
            this.user = user;
            return this;
        }

        public TableReservationResponse build() {
            return new TableReservationResponse(this);
        }
    }
}


