package com.Cesde.concesionario.Servicio;

import com.Cesde.concesionario.Dto.FacturasCompletasDTO;
import com.Cesde.concesionario.Mapper.IFacturasCompletasMapper;
import com.Cesde.concesionario.Repositorio.IFactura;
import com.Cesde.concesionario.Repositorio.IFacturasCompletasProjection;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SFactura implements IFacturaCompletaService{
    private final IFactura iFactura;
    private final IFacturasCompletasMapper iFacturasCompletasMapper;
    // Constructor
    public SFactura(IFactura iFactura, IFacturasCompletasMapper iFacturasCompletasMapper) {
        this.iFactura = iFactura;
        this.iFacturasCompletasMapper = iFacturasCompletasMapper;
    }
    // Implementando el metodo de la interface
    @Override
    public List<FacturasCompletasDTO> obtenerFacturasCompletasDTO(String idCliente) {
      List<IFacturasCompletasProjection> proyeccion=iFactura.buscarFacturasCliente(idCliente);
      return iFacturasCompletasMapper.toDTOList(proyeccion);
    }
}
