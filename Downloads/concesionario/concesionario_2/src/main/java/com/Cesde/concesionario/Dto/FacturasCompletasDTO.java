package com.Cesde.concesionario.Dto;

import java.time.LocalDate;

public record FacturasCompletasDTO(String nombrecliente,
              String telefonocliente, Integer codfactura,
              LocalDate fecha,String idcliente,String placa,
              Double valventa,String marca,String modelo) {
}
