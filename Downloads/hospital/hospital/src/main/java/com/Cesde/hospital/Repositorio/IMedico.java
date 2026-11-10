package com.Cesde.hospital.Repositorio;

import com.Cesde.hospital.Modelo.MMedico;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IMedico extends JpaRepository<MMedico,String> {
}
