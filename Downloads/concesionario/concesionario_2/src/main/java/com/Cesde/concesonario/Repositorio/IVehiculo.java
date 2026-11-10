package com.Cesde.concesonario.Repositorio;

import com.Cesde.concesonario.Modelo.MVehiculo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IVehiculo extends JpaRepository<MVehiculo,String> {
    // Consultas por placa y modelo
    List<MVehiculo> findByMarca(String marca);
    List<MVehiculo> findByModelo(String modelo);
}
