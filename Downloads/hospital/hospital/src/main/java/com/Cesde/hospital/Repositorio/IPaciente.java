package com.Cesde.hospital.Repositorio;

import com.Cesde.hospital.Modelo.MPaciente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IPaciente extends JpaRepository<MPaciente,String> {
}
