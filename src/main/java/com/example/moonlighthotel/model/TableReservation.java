package com.example.moonlighthotel.model;


import javax.persistence.*;
import javax.persistence.Table;
import java.time.Instant;

@Entity
@Table(name = "table_reservations")
public class TableReservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id;

    //Reservation date. ISO-8601 in UTC string
    //TO DO
    private Instant date;

    private int people;

    private double price;

    private String updated;

    @ManyToOne
    @JoinColumn(name = "table_id")
    private com.example.moonlighthotel.model.Table table;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public TableReservation() {
    }

    public TableReservation(Long id, Instant date, int people, double price, String updated,
                            com.example.moonlighthotel.model.Table table, User user) {
        this.id = id;
        this.date = date;
        this.people = people;
        this.price = price;
        this.updated = updated;
        this.table = table;
        this.user = user;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Instant getDate() {
        return date;
    }

    public void setDate(Instant date) {
        this.date = date;
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

    public String getUpdated() {
        return updated;
    }

    public void setUpdated(String updated) {
        this.updated = updated;
    }

    public com.example.moonlighthotel.model.Table getTable() {
        return table;
    }

    public void setTable(com.example.moonlighthotel.model.Table table) {
        this.table = table;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}


