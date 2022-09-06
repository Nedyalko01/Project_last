package com.example.moonlighthotel.repositories;

import com.example.moonlighthotel.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

    Optional<Role> findByAuthority(String authority);//еднакви имена със Entity properties -> private String name;


}
