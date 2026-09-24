package com.example.erecepta.repository;

import com.example.erecepta.entity.Lek;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LekRepository extends JpaRepository<Lek, Integer> {

    Optional<Lek> findLekByIDLeku(Integer idLeku);
}
