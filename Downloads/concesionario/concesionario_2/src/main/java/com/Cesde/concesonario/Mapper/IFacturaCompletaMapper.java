package com.Cesde.concesonario.Mapper;

import com.Cesde.concesonario.Dto.FacturasCompletasClienteDTO;
import com.Cesde.concesonario.Repositorio.IFacturasCompletasClienteProjecion;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import java.util.List;

@Mapper(componentModel = "spring")
public interface IFacturaCompletaMapper {
    @Mapping(source = "nomcliente",target = "nombrecliente")
    @Mapping(source="telcliente",target = "telefonocliente")
    FacturasCompletasClienteDTO toDTO(IFacturasCompletasClienteProjecion p);
    List<FacturasCompletasClienteDTO> toDTOList(
            List<IFacturasCompletasClienteProjecion> projections
    );
}
