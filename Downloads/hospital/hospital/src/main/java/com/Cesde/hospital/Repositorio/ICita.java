package com.Cesde.hospital.Repositorio;

import com.Cesde.hospital.Modelo.MCita;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ICita extends JpaRepository<MCita,Integer> {
}
