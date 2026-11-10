package com.Cesde.concesonario.Servicio;

import com.Cesde.concesonario.Dto.FacturasClienteDTO;
import com.Cesde.concesonario.Modelo.MCliente;
import com.Cesde.concesonario.Modelo.MFactura;
import com.Cesde.concesonario.Repositorio.ICliente;
import com.Cesde.concesonario.Repositorio.IFactura;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class SCliente {

    private final ICliente icliente;
    private final IFactura iFactura;

    // Constructor
    public SCliente(ICliente icliente, IFactura iFactura) {
        this.icliente = icliente;
        this.iFactura = iFactura;
    }

    // Operaciones
    // Adicionar un cliente
    public MCliente adicionarCliente (MCliente mCliente) throws Exception{
        try{
            return this.icliente.save(mCliente);
        }catch (Exception error){
            throw new Exception(error.getMessage());
        }
    }

    // Consulta general
    public List<MCliente> listarClientes() throws Exception{
        try{
            return this.icliente.findAll();
        }catch (Exception error){
            throw new Exception(error.getMessage());
        }
    }

    // Consulta individual por llave primaria
    public MCliente consultarClienteId (String idcliente) throws Exception{
        try{
            Optional<MCliente> clienteEncontrado=this.icliente.findById(idcliente);
            if (clienteEncontrado.isPresent()){
                return clienteEncontrado.get();
            }else
                throw new Exception("Cliente no encontrado");
        }catch (Exception error){
            throw new Exception(error.getMessage());
        }
    }

    // Consulta por nombre de cliente
    public List<MCliente> consultarClienteNom(String nomcliente) throws Exception{
        try{
            return this.icliente.findByNomcliente(nomcliente);
        }catch (Exception error){
            throw new Exception(error.getMessage());
        }
    }

    // Consulta de las facturas de un cliente
    public List<FacturasClienteDTO> consultarFacturasCliente (String idcliente) throws Exception{
        try{
            return this.icliente.buscarFacturasCliente(idcliente);
        }catch (Exception error){
            throw new Exception(error.getMessage());
        }
    }

    // Modificar un registro
    public MCliente actualizarCliente (MCliente mCliente, String idcliente) throws Exception{
        try{
            Optional<MCliente> clienteEncontrado= this.icliente.findById(idcliente);
            if (clienteEncontrado.isPresent()) {
                MCliente clienteNuevo=clienteEncontrado.get();
                clienteNuevo.setIdcliente(idcliente);
                clienteNuevo.setNomcliente(mCliente.getNomcliente());
                clienteNuevo.setDircliente(mCliente.getDircliente());
                clienteNuevo.setTelcliente(mCliente.getTelcliente());
                clienteNuevo.setActivo(mCliente.getActivo());
                return this.icliente.save(clienteNuevo);
            }else
                throw new Exception("Cliente no se puede modificar porque no esta registrado");
        }catch (Exception error){
            throw new Exception(error.getMessage());
        }
    }

    // Eliminar un registro
    public Boolean eliminarCliente (String idCliente) throws Exception{
        try{
            Optional<MCliente> clienteEncontrado= this.icliente.findById(idCliente);
            if (clienteEncontrado.isPresent()) {
               List<MFactura> facturaEncontrada= this.iFactura.findByIdcliente(idCliente);
               if  (facturaEncontrada.isEmpty()) {
                   this.icliente.deleteById(idCliente);
                   return true;
               } else  {
                   throw new Exception("No se puede eliminar porque tiene facturas relacionadas");
               }
            }else
                throw new Exception("Cliente no encontrado");
        }catch (Exception error){
            throw new Exception(error.getMessage());
        }
    }
}
