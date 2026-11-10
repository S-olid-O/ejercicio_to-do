package com.Cesde.concesonario.Repositorio;

import com.Cesde.concesonario.Dto.FacturasClienteDTO;
import com.Cesde.concesonario.Modelo.MCliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ICliente extends JpaRepository<MCliente,String> {
    // Consulta por nombre
    List<MCliente> findByNomcliente(String Nomcliente);
    // Consulta de facturas que compro un cliente
    @Query(value = "select C.nomcliente, C.dircliente, F.codfactura,F.fecha,F.idcliente" +
    " from cliente C inner join factura F on C.idcliente=F.idcliente " +
    " where F.idcliente = :idcliente"
    ,nativeQuery = true)
    List<FacturasClienteDTO> buscarFacturasCliente (@Param("idcliente") String idcliente);
}
