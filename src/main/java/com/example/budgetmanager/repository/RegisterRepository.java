package com.example.budgetmanager.repository;

import com.example.budgetmanager.model.entities.Register;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RegisterRepository extends JpaRepository<Register, Integer> {
    Optional<Register> findFirstByNameOrderByTimestampDesc(String name);
}
