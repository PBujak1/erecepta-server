package com.example.erecepta.repository;

import com.example.erecepta.model.Lekarz;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LekarzRepository extends JpaRepository<Lekarz, Integer> {
    Optional<Lekarz> findByPesel(Integer pesel);

    Optional<Lekarz> findByPeselAndNazwisko(Integer pesel, String nazwisko);
}
