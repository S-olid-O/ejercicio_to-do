package com.Cesde.concesionario.Modelo;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

@Entity
@Table(name = "vehiculofactura")
public class MVehiculoFactura {
    // Atributos
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer consecutivo;
    @Column(nullable = false)
    private Integer codfactura;
    @Column(length = 6,nullable = false)
    private String placa;
    @Column(nullable = false)
    private Double valventa;

    // Relacion entre la tabla de vehiculofactura y la tabla vehiculo
    @ManyToOne
    @JoinColumn(name = "pkvehiculo",referencedColumnName = "placa")
    //@JsonBackReference
    private MVehiculo vehiculo;

    // Relacion entre la tabla vehiculofactura y factura
    @ManyToOne
    @JoinColumn(name = "pkdetalle",referencedColumnName = "codfactura")
    //@JsonBackReference
    private MFactura factura;

    public MVehiculo getVehiculo() {
        return vehiculo;
    }

    public void setVehiculo(MVehiculo vehiculo) {
        this.vehiculo = vehiculo;
    }

    // Constructores
    public MVehiculoFactura(Integer consecutivo, Integer codfactura, String placa, Double valventa) {
        this.consecutivo = consecutivo;
        this.codfactura = codfactura;
        this.placa = placa;
        this.valventa = valventa;
    }
    public MVehiculoFactura() {
    }

    // Encapsulamiento
    public Integer getConsecutivo() {
        return consecutivo;
    }
    public void setConsecutivo(Integer consecutivo) {
        this.consecutivo = consecutivo;
    }

    public Integer getCodfactura() {
        return codfactura;
    }
    public void setCodfactura(Integer codfactura) {
        this.codfactura = codfactura;
    }

    public String getPlaca() {
        return placa;
    }
    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public Double getValventa() {
        return valventa;
    }
    public void setValventa(Double valventa) {
        this.valventa = valventa;
    }
}
