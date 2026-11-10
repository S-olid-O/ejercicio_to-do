package com.Cesde.concesionario.Dto;

import java.time.LocalDate;

public class FacturasClienteDTO {
    // Atributos de la consulta
    private Integer codfactura;
    private LocalDate fecha;
    private String idcliente;
    private String nomcliente;
    private String telcliente;

    // Constructores
    public FacturasClienteDTO(Integer codfactura, LocalDate fecha, String idcliente, String nomcliente, String telcliente) {
        this.codfactura = codfactura;
        this.fecha = fecha;
        this.idcliente = idcliente;
        this.nomcliente = nomcliente;
        this.telcliente = telcliente;
    }
    public FacturasClienteDTO() {
    }

    // Encapsulamiento
    public Integer getCodfactura() {
        return codfactura;
    }
    public void setCodfactura(Integer codfactura) {
        this.codfactura = codfactura;
    }

    public LocalDate getFecha() {
        return fecha;
    }
    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public String getIdcliente() {
        return idcliente;
    }
    public void setIdcliente(String idcliente) {
        this.idcliente = idcliente;
    }

    public String getNomcliente() {
        return nomcliente;
    }
    public void setNomcliente(String nomcliente) {
        this.nomcliente = nomcliente;
    }

    public String getTelcliente() {
        return telcliente;
    }
    public void setTelcliente(String telcliente) {
        this.telcliente = telcliente;
    }
}
