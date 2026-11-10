package com.Cesde.concesionario.Repositorio;

import com.Cesde.concesionario.Modelo.MVehiculoFactura;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IVehiculoFactura extends JpaRepository<MVehiculoFactura,Integer> {
}
