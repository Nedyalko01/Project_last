package com.example.moonlighthotel.repositories;

import com.example.moonlighthotel.model.Table;
import com.example.moonlighthotel.model.TableReservation;
import com.example.moonlighthotel.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TableReservationRepository extends JpaRepository<TableReservation, Long> {

    List<TableReservation> findByTable(Table table);

    List<TableReservation> findByUser(User user);
}
