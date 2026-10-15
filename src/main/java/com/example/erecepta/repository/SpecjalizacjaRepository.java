package com.example.erecepta.repository;

import com.example.erecepta.entity.Lekarz;
import com.example.erecepta.entity.Specjalizacja;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface SpecjalizacjaRepository extends JpaRepository<Specjalizacja, Integer> {

    @Query("""
        SELECT s FROM Specjalizacja s
        WHERE LOWER(s.NazwaSpecjalizacji) LIKE LOWER(CONCAT('%', :fraza, '%'))
        ORDER BY s.NazwaSpecjalizacji
    """)
    List<Specjalizacja> szukajSpecjalizacji(@Param("fraza") String fraza, Pageable pageable);

}
