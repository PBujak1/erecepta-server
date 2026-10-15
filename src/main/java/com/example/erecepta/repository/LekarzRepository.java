package com.example.erecepta.repository;

import com.example.erecepta.entity.Lekarz;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import org.springframework.data.domain.Pageable;
import java.util.List;
import java.util.Optional;

public interface LekarzRepository extends JpaRepository<Lekarz, Integer> {
    Optional<Lekarz> findByPesel(String pesel);

    Optional<Lekarz> findByPeselAndNazwisko(String pesel, String nazwisko);

    Optional<Lekarz> findByNazwiskoAndPesel(String nazwisko, String pesel);

    Optional<Lekarz> findByIdLekarza(Integer IdLekarza);

    @Query("""
        SELECT l FROM Lekarz l
        WHERE LOWER(CONCAT(l.imie, ' ', l.nazwisko)) LIKE LOWER(CONCAT('%', :fraza, '%'))
        OR LOWER(CONCAT(l.nazwisko, ' ', l.imie)) LIKE LOWER(CONCAT('%', :fraza, '%'))
        ORDER BY l.nazwisko, l.imie
    """)
    List<Lekarz> szukajLekarzy(@Param("fraza") String fraza, Pageable pageable);

    Optional<Lekarz> findByImieAndNazwisko(String imie, String nazwisko);
}
