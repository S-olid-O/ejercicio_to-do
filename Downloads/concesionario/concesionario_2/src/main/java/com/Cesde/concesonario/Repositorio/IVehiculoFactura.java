package com.Cesde.concesonario.Repositorio;

import com.Cesde.concesonario.Modelo.MVehiculoFactura;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IVehiculoFactura extends JpaRepository<MVehiculoFactura,Integer> {
}
