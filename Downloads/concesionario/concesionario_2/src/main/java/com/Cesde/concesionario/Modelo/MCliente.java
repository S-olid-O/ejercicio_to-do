package com.Cesde.concesionario.Modelo;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "cliente")
public class MCliente {
    // Atributos
    @Id
    @Schema(description = "LLave primaria",example = "10101010")
    @Column(length = 15,nullable = false)
    private String idcliente;
    @Column(length = 100,nullable = false)
    @Schema(description = "Nombre cliente maximo 100 caracteres",
           example = "Pedro Paramo")
    private String nomcliente;
    @Column(length = 50,nullable = false)
    @Schema(description = "Direccion del cliente maximo 50 caracteres",
           example = "Km 5 carretera al mar")
    private String dircliente;
    @Column(length = 10,nullable = false)
    @Schema(description = "Telefono celular o fijo del cliente, maximo 10 caracteres",
            example = "3134569012")
    private String telcliente;
    @Column(nullable = false)
    @Schema(description = "Campo logica para activar/desactivar un cliente",
           example="true")
    private Boolean activo;

    // Relaciones entre clase cliente y facturas
    @OneToMany(mappedBy = "clientes")
    //@JsonManagedReference
    private List<MFactura> facturas;

    // Constructores
    public MCliente(String idcliente, String nomcliente, String dircliente, String telcliente, Boolean activo) {
        this.idcliente = idcliente;
        this.nomcliente = nomcliente;
        this.dircliente = dircliente;
        this.telcliente = telcliente;
        this.activo = activo;
    }
    public MCliente() {
    }

    // Encapsulamiento
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

    public String getDircliente() {
        return dircliente;
    }
    public void setDircliente(String dircliente) {
        this.dircliente = dircliente;
    }

    public String getTelcliente() {
        return telcliente;
    }
    public void setTelcliente(String telcliente) {
        this.telcliente = telcliente;
    }

    public Boolean getActivo() {
        return activo;
    }
    public void setActivo(Boolean activo) {
        this.activo = activo;
    }
}
