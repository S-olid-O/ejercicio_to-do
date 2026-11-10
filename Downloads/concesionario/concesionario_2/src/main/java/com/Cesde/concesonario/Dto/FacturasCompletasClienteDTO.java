package com.Cesde.concesonario.Dto;

import java.time.LocalDate;

public record FacturasCompletasClienteDTO (String nombrecliente, String telefonocliente,
       Integer codfactura, LocalDate fecha,String idcliente,String placa,
       Integer valventa,String modelo,String marca) {
}
