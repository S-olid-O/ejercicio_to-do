package com.Cesde.concesonario.Modelo;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "vehiculo")
public class MVehiculo {
    // Atributos
    @Id
    @Column(length=6,nullable=false)
    public String placa;
    @Column(length=25,nullable=false)
    public String marca;
    @Column(length=4,nullable=false)
    public String modelo;
    @Column(nullable = false)
    public Integer valor;
    @Column(nullable=false)
    Boolean estado;

    // Relaciones entre vehiculo y vehiculofactura
  /*  @OneToMany(mappedBy = "vehiculo")
    @JsonManagedReference
    private List<MVehiculoFactura> facturas;*/

    // Constructores
    public MVehiculo(String placa, String marca, String modelo, Integer valor, Boolean estado) {
        this.placa = placa;
        this.marca = marca;
        this.modelo = modelo;
        this.valor = valor;
        this.estado = estado;
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

    public Integer getValor() {
        return valor;
    }
    public void setValor(Integer valor) {
        this.valor = valor;
    }

    public Boolean getEstado() {
        return estado;
    }
    public void setEstado(Boolean estado) {
        this.estado = estado;
    }
}
