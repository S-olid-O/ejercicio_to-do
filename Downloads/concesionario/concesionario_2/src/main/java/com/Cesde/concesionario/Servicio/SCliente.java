package com.Cesde.concesionario.Servicio;

import com.Cesde.concesionario.Dto.FacturasClienteDTO;
import com.Cesde.concesionario.Modelo.MCliente;
import com.Cesde.concesionario.Modelo.MFactura;
import com.Cesde.concesionario.Repositorio.ICliente;
import com.Cesde.concesionario.Repositorio.IFactura;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class SCliente {

    private final ICliente iCliente;
    private final IFactura iFactura;

    // Constructor
    public SCliente(ICliente iCliente, IFactura iFactura) {
        this.iCliente = iCliente;
        this.iFactura = iFactura;
    }

    // Adicionar un registro cliente
    public MCliente adicionarCliente(MCliente mCliente) throws Exception{
        try{
            return this.iCliente.save(mCliente);
        }catch (Exception error){
            throw new Exception(error.getMessage());
        }
    }

    // Consulta general
    public List<MCliente> consultaGeneralCli() throws Exception{
        try{
            return this.iCliente.findAll();
        }catch (Exception error){
            throw new Exception(error.getMessage());
        }
    }

    // Consulta individual por llave primaria
    public MCliente consultaIndividualId(String idCliente) throws Exception{
        try{
            Optional<MCliente> clienteEncontrado=this.iCliente.findById(idCliente);
            if (clienteEncontrado.isPresent())
                return clienteEncontrado.get();
            else
                throw new Exception("Cliente no registrado");
        }catch (Exception error){
            throw new Exception(error.getMessage());
        }
    }

    // Consulta por el nombre del cliente
    public List<MCliente> consultaIndividualNom(String nomCliente) throws Exception{
        try{
            return this.iCliente.findByNomcliente(nomCliente);
        }catch (Exception error){
            throw new Exception(error.getMessage());
        }
    }

    // Consulta de las facturas de un cliente
    public List<FacturasClienteDTO> consultaFacturaCliente(String idCliente) throws Exception{
        try{
            return this.iCliente.buscarFacturasCliente(idCliente);
        }catch (Exception error){
            throw new Exception(error.getMessage());
        }
    }

    // Modificar un registro cliente
    public MCliente modificarCliente(MCliente mCliente,String idCliente) throws Exception{
        try{
            Optional<MCliente> clienteEncontrado=this.iCliente.findById(idCliente);
            if (clienteEncontrado.isPresent()){
                MCliente clienteNuevo=clienteEncontrado.get();
                clienteNuevo.setIdcliente(idCliente);
                clienteNuevo.setNomcliente(mCliente.getNomcliente());
                clienteNuevo.setDircliente(mCliente.getDircliente());
                clienteNuevo.setTelcliente(mCliente.getTelcliente());
                clienteNuevo.setActivo(mCliente.getActivo());
                return this.iCliente.save(clienteNuevo);
            }else
                throw new Exception("No se puede modificar porque cliente no registrado");
        } catch (Exception error) {
            throw new Exception(error.getMessage());
        }
    }

    // Eliminar un registro cliente
    public Boolean eliminarCliente(String idCliente) throws Exception{
        try{
            Optional<MCliente> clienteEncontrado=this.iCliente.findById(idCliente);
            if (clienteEncontrado.isPresent()){
                List<MFactura> facturaEncontrada=this.iFactura.findByIdcliente(idCliente);
                if (facturaEncontrada.isEmpty()){
                    this.iCliente.deleteById(idCliente);
                    return true;
                }else
                    throw new Exception("El cliente tiene facturas relacionadas");
            }else
                throw new Exception("No se puede eliminar porque cliente no registrado");
        }catch (Exception error){
            throw new Exception(error.getMessage());
        }
    }
}
