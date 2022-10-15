package com.example.moonlighthotel.dto.restaurant;

import com.example.moonlighthotel.enumerations.TableZone;

public class TableResponse {

    private Long id;

    private TableZone zone;

    private int number;

    private int people;

    private boolean smoking;

    public TableResponse(Builder builder) {
        this.id = builder.id;
        this.zone = builder.zone;
        this.number = builder.number;
        this.people = builder.people;
        this.smoking = builder.smoking;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public TableZone getTableZone() {
        return zone;
    }

    public void setTableZone(TableZone tableZone) {
        this.zone = tableZone;
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

    public boolean isSmoking() {
        return smoking;
    }

    public void setSmoking(boolean smoking) {
        this.smoking = smoking;
    }

    public static class Builder {

        private Long id;
        private TableZone zone;
        private int number;
        private int people;
        private boolean smoking;

        public Builder addId(Long id) {
            this.id = id;
            return this;
        }

        public Builder addZone(TableZone zone) {
            this.zone = zone;
            return this;
        }

        public Builder addNumber(int number) {
            this.number = number;
            return this;
        }

        public Builder addPeople(int people) {
            this.people = people;
            return this;
        }

        public Builder addSmoking(boolean smoking) {
            this.smoking = smoking;
            return this;
        }

        public TableResponse build() {
            return new TableResponse(this);
        }
    }
}