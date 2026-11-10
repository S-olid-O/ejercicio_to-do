package com.Cesde.concesionario.Repositorio;

import com.Cesde.concesionario.Modelo.MVehiculo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IVehiculo extends JpaRepository<MVehiculo,String> {
    // Metodos para consultas por marca y modelo
    List<MVehiculo> findByMarca (String Marca);
    List<MVehiculo> findByModelo (String Modelo);
}
