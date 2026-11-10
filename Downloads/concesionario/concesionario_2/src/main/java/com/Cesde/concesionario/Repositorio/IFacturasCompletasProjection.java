package com.Cesde.concesionario.Repositorio;

import java.time.LocalDate;

public interface IFacturasCompletasProjection {
    String getNomcliente();
    String getTelcliente();
    Integer getCodfactura();
    LocalDate getFecha();
    String getIdcliente();
    String getPlaca();
    Double getValventa();
    String getMarca();
    String getModelo();
}
