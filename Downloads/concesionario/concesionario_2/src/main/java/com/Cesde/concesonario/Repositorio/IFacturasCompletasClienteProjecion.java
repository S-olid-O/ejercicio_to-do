package com.Cesde.concesonario.Repositorio;

import java.time.LocalDate;

public interface IFacturasCompletasClienteProjecion {
    String getNomcliente();
    String getTelcliente();
    Integer getCodfactura();
    LocalDate getFecha();
    String getIdcliente();
    String getPlaca();
    String getValventa();
    String getMarca();
    String getModelo();
}
