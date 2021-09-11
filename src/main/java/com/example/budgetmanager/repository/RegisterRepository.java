package com.example.budgetmanager.repository;

import com.example.budgetmanager.model.entities.Register;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RegisterRepository extends JpaRepository<Register, Integer> {
    Optional<Register> findFirstByNameOrderByTimestampDesc(String name);

    @Query(nativeQuery = true,
            value = "SELECT id, name, value, timestamp\n" +
                    "FROM (SELECT id, name, value, timestamp,\n" +
                    "ROW_NUMBER() OVER (PARTITION BY name ORDER BY timestamp desc) AS row_number\n" +
                    "FROM register) AS rows\n" +
                    "WHERE rows.row_number = 1")
    List<Register> getCurrentBalance();
}
