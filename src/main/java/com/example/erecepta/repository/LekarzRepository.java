package com.example.erecepta.repository;

import com.example.erecepta.entity.Lekarz;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface LekarzRepository extends JpaRepository<Lekarz, Integer> {
    Optional<Lekarz> findByPesel(String pesel);

    Optional<Lekarz> findByPeselAndNazwisko(String pesel, String nazwisko);

    Optional<Lekarz> findByNazwiskoAndPesel(String nazwisko, String pesel);

    Optional<Lekarz> findByIdLekarza(Integer IdLekarza);
}
