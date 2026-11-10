package com.Cesde.concesonario.Modelo;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "cliente")
public class MCliente {
    // Atributos
    @Id
    @Column(length=15,nullable=false)
    private String idcliente;
    @Column(length=70,nullable=false)
    private String nomcliente;
    @Column(length=50,nullable=false)
    private String dircliente;
    @Column(length=10,nullable=false)
    private String telcliente;
    @Column(length=2,nullable=false)
    private String activo;

    // Relaciones entre cliente y factura
  /* @OneToMany(mappedBy = "cliente")
   @JsonManagedReference
   private List<MFactura> facturas;*/

    // Constructores
    public MCliente(String idcliente, String nomcliente, String dircliente, String telcliente, String activo) {
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

    public String getActivo() {
        return activo;
    }
    public void setActivo(String activo) {
        this.activo = activo;
    }
}
