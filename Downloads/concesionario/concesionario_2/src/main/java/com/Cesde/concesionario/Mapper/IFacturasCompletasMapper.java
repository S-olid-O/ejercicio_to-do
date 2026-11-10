package com.Cesde.concesionario.Mapper;

import com.Cesde.concesionario.Dto.FacturasCompletasDTO;
import com.Cesde.concesionario.Repositorio.IFacturasCompletasProjection;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface IFacturasCompletasMapper {
    // Mapping
    @Mapping(source="nomcliente",target ="nombrecliente")
    @Mapping(source = "telcliente",target = "telefonocliente")
    FacturasCompletasDTO toDTO(IFacturasCompletasProjection p);
    List<FacturasCompletasDTO> toDTOList(List<IFacturasCompletasProjection> projeccion);
}
