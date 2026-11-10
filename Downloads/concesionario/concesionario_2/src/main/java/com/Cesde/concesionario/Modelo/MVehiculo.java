package com.Cesde.concesionario.Modelo;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "vehiculo")
public class MVehiculo {
    // Atributos
    @Id
    @Column(length = 6,nullable = false)
    private String placa;
    @Column(length = 25,nullable = false)
    private String marca;
    @Column(length = 4,nullable = false)
    private String modelo;
    @Column(nullable = false)
    private Double valor;
    @Column(nullable = false)
    private Boolean activo;

    // Relaciones entre tabla vehiculo y vehiculofactura
    @OneToMany(mappedBy = "vehiculo")
    //@JsonManagedReference
    private List<MVehiculoFactura> vehiculoFacturas;

    // Constructores
    public MVehiculo(String placa, String marca, String modelo, Double valor, Boolean activo) {
        this.placa = placa;
        this.marca = marca;
        this.modelo = modelo;
        this.valor = valor;
        this.activo = activo;
    }
    public MVehiculo() {
    }

    // Encapsulamiento
    public String getPlaca() {
        return placa;
    }
    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public String getMarca() {
        return marca;
    }
    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModelo() {
        return modelo;
    }
    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public Double getValor() {
        return valor;
    }
    public void setValor(Double valor) {
        this.valor = valor;
    }

    public Boolean getActivo() {
        return activo;
    }
    public void setActivo(Boolean activo) {
        this.activo = activo;
    }
}
