package com.example.erecepta.repository;

import com.example.erecepta.entity.ReceptaPozycja;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReceptaPozycjaRepository extends JpaRepository<ReceptaPozycja, Integer> {
    List<ReceptaPozycja> findByIdRecepty(Integer idRecepty);
}
