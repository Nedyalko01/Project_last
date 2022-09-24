package com.example.moonlighthotel.repositories;

import com.example.moonlighthotel.model.Room;
import com.example.moonlighthotel.model.RoomReservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.Instant;
import java.util.List;

@Repository
public interface RoomReservationRepository extends JpaRepository<RoomReservation, Long> {


    @Query("select r, COUNT(*) from Room r where r.people >= :people or r.id not in " +
            "(select rr.room from RoomReservation rr) and r.id in (select rrm.room from RoomReservation rrm where " +
            "rrm.checkIn not between :start and :end and rrm.checkOut not between :start and :end) group by r having COUNT(*) <= r.count")
    List<Room> findRoomByPeriodAndPeople(@Param("start") Instant start, @Param("end") Instant end, @Param("people") int people);
}


