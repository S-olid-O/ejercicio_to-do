package com.Cesde.hospital.Modelo;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.LocalDate;

@Entity
@Table(name = "cita")
public class MCita {
    @Id
    private Integer codcita;
    @Column(nullable = false)
    private LocalDate fecha;
    @Column(length = 10, nullable = false)
    private String idpaciente;
    @Column(length = 10, nullable = false)
    private String idmedico;
    private Boolean activo;

    // Constructores
    public MCita(Integer codcita, LocalDate fecha, String idpaciente, String idmedico, Boolean activo) {
        this.codcita = codcita;
        this.fecha = fecha;
        this.idpaciente = idpaciente;
        this.idmedico = idmedico;
        this.activo = activo;
    }
    public MCita() {
    }

    // Encapsulamiento
    public Integer getCodcita() {
        return codcita;
    }
    public void setCodcita(Integer codcita) {
        this.codcita = codcita;
    }

    public LocalDate getFecha() {
        return fecha;
    }
    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public String getIdpaciente() {
        return idpaciente;
    }
    public void setIdpaciente(String idpaciente) {
        this.idpaciente = idpaciente;
    }

    public String getIdmedico() {
        return idmedico;
    }
    public void setIdmedico(String idmedico) {
        this.idmedico = idmedico;
    }

    public Boolean getActivo() {
        return activo;
    }
    public void setActivo(Boolean activo) {
        this.activo = activo;
    }
}
