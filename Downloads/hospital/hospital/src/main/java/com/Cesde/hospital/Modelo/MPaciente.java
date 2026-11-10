package com.Cesde.hospital.Modelo;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="paciente")
public class MPaciente {
    @Id
    @Column(length = 10,nullable = false)
    private String idpaciente;
    @Column(length = 100,nullable = false)
    private String nompaciente;
    @Column(length = 10,nullable = false)
    private String telpaciente;
    @Column(nullable = false)
    private Boolean activo;

    // Constructores
    public MPaciente(String idpaciente, String nompaciente, String telpaciente, Boolean activo) {
        this.idpaciente = idpaciente;
        this.nompaciente = nompaciente;
        this.telpaciente = telpaciente;
        this.activo = activo;
    }
    public MPaciente() {
    }

    // Encapsulamiento
    public String getIdpaciente() {
        return idpaciente;
    }
    public void setIdpaciente(String idpaciente) {
        this.idpaciente = idpaciente;
    }

    public String getNompaciente() {
        return nompaciente;
    }
    public void setNompaciente(String nompaciente) {
        this.nompaciente = nompaciente;
    }

    public String getTelpaciente() {
        return telpaciente;
    }
    public void setTelpaciente(String telpaciente) {
        this.telpaciente = telpaciente;
    }

    public Boolean getActivo() {
        return activo;
    }
    public void setActivo(Boolean activo) {
        this.activo = activo;
    }
}
