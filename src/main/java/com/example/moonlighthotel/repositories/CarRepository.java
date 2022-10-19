package com.example.moonlighthotel.repositories;

import com.example.moonlighthotel.model.car.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.Instant;
import java.util.List;

@Repository
public interface CarRepository extends JpaRepository<Car, Long> {

    @Query("SELECT c FROM Car c WHERE c.category.seats >= :seats AND" +
            " c IN (SELECT ct.car FROM CarTransfer ct WHERE ct.date != :date) OR " +
            "c.category.seats >= :seats AND c NOT IN (SELECT ct.car FROM CarTransfer ct)")
    List<Car> findCarsBySeatsAndCarTransferDate(@Param("seats") int seats, @Param("date") Instant date);
}

