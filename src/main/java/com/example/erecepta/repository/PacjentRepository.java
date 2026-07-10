package com.example.erecepta.repository;

import com.example.erecepta.model.Pacjent;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PacjentRepository extends JpaRepository<Pacjent, Integer> {

    Optional<Pacjent> findByNazwiskoAndPesel(String nazwisko, String pesel);

    Optional<Pacjent> findByPesel(String pesel);

}