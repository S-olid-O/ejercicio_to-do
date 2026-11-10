package com.Cesde.concesionario.Servicio;

import com.Cesde.concesionario.Dto.FacturasCompletasDTO;
import java.util.List;

public interface IFacturaCompletaService {
    List<FacturasCompletasDTO> obtenerFacturasCompletasDTO(String idCliente);
}
