package com.example.moonlighthotel.model.table;

import com.example.moonlighthotel.enumerations.TableZone;

import javax.persistence.*;
import java.util.List;

@Entity
@javax.persistence.Table(name = "tables")
public class Table {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private TableZone zone;

    private int number;

    private int people;

    private boolean smoking;

    @OneToMany(mappedBy = "table")
    private List<TableReservation> tableReservations;

    public Table() {
    }

    public Table(Long id, TableZone zone, int number, int people, boolean smoking, List<TableReservation> tableReservations) {
        this.id = id;
        this.zone = zone;
        this.number = number;
        this.people = people;
        this.smoking = smoking;
        this.tableReservations = tableReservations;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public boolean isSmoking() {
        return smoking;
    }

    public void setSmoking(boolean smoking) {
        this.smoking = smoking;
    }

    public List<TableReservation> getTableReservations() {
        return tableReservations;
    }

    public void setTableReservations(List<TableReservation> tableReservations) {
        this.tableReservations = tableReservations;
    }
}

