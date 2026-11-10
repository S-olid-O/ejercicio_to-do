package com.Cesde.hospital.Modelo;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="medico")
public class MMedico {
    @Id
    @Column(length = 10,nullable = false)
    private String idmedico;
    @Column(length = 100,nullable = false)
    private String nommedico;
    @Column(length = 10,nullable = false)
    private String telmedico;
    @Column(nullable = false)
    private Boolean activo;

    // Constructores
    public MMedico(String idmedico, String nommedico, String telmedico, Boolean activo) {
        this.idmedico = idmedico;
        this.nommedico = nommedico;
        this.telmedico = telmedico;
        this.activo = activo;
    }
    public MMedico() {
    }

    // Encapsulamiento
    public String getIdmedico() {
        return idmedico;
    }
    public void setIdmedico(String idmedico) {
        this.idmedico = idmedico;
    }

    public String getNommedico() {
        return nommedico;
    }
    public void setNommedico(String nommedico) {
        this.nommedico = nommedico;
    }

    public String getTelmedico() {
        return telmedico;
    }
    public void setTelmedico(String telmedico) {
        this.telmedico = telmedico;
    }

    public Boolean getActivo() {
        return activo;
    }
    public void setActivo(Boolean activo) {
        this.activo = activo;
    }
}
