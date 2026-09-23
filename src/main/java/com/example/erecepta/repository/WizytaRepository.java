package com.example.erecepta.repository;

import com.example.erecepta.entity.Wizyta;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface WizytaRepository extends JpaRepository<Wizyta, Integer> {

    List<Wizyta> findByIDPacjenta(Integer IDPacjenta);
}
