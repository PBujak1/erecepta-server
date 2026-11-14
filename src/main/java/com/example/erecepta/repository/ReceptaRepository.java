package com.example.erecepta.repository;

import com.example.erecepta.entity.Recepta;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ReceptaRepository extends JpaRepository<Recepta, Integer> {

    Optional<Recepta> findByIDRecepty(Integer idRecepty);
    Optional<Recepta> findByIDWizyty(Integer idWizyty);
}
