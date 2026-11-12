package com.example.erecepta.repository;

import com.example.erecepta.entity.Alergie;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AlergieRepository extends JpaRepository<Alergie, Integer> {

    List<Alergie> findByIDPacjenta(Integer idPacjenta);

}
