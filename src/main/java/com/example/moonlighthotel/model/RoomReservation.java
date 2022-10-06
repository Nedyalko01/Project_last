package com.example.moonlighthotel.model;


import com.example.moonlighthotel.enumerations.BedType;
import lombok.Builder;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.Instant;

@Entity
@Builder
@Table(name = "room_reservations")
public class RoomReservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    private Instant createdAt;
    @NotNull
    private Instant checkIn;
    @NotNull
    private Instant checkOut;
    @NotNull
    private Integer adults;

    @NotNull
    private Integer kids;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    @NotNull
    private Double totalPrice;
    @NotNull
    @Enumerated(EnumType.STRING)
    private BedType facilities;

    @ManyToOne
    @JoinColumn(name = "room_id", referencedColumnName = "id")
    private Room room;

    public RoomReservation() {
    }



    public RoomReservation(Long id, Instant createdAt,
                           @NotNull Instant checkIn,
                           @NotNull Instant checkOut,
                           @NotNull Integer adults,
                           @NotNull Integer kids,
                           @NotNull User user,
                           @NotNull Double totalPrice,
                           @NotNull BedType facilities,
                           Room room) {
        this.id = id;
        this.createdAt = createdAt;
        this.checkIn = checkIn;
        this.checkOut = checkOut;
        this.adults = adults;
        this.kids = kids;
        this.user = user;
        this.totalPrice = totalPrice;
        this.facilities = facilities;
        this.room = room;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Instant createdAt) {
        this.createdAt = createdAt;
    }

    public Instant getCheckIn() {
        return checkIn;
    }

    public void setCheckIn(Instant checkIn) {
        this.checkIn = checkIn;
    }

    public Instant getCheckOut() {
        return checkOut;
    }

    public void setCheckOut(Instant checkOut) {
        this.checkOut = checkOut;
    }

    public Integer getAdults() {
        return adults;
    }

    public void setAdults(Integer adults) {
        this.adults = adults;
    }

    public Integer getKids() {
        return kids;
    }

    public void setKids(Integer kids) {
        this.kids = kids;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public BedType getFacilities() {
        return facilities;
    }

    public void setFacilities(BedType facilities) {
        this.facilities = facilities;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }
}