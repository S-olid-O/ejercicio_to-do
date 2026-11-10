package com.Cesde.concesionario.Repositorio;

import com.Cesde.concesionario.Dto.FacturasClienteDTO;
import com.Cesde.concesionario.Modelo.MCliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ICliente extends JpaRepository <MCliente,String> {
    // Consulta por nombre de cliente
    List<MCliente> findByNomcliente (String Nomcliente);

    /* Consulta mas avanzada para que me muestre las facturas que me
    compro un cliente*/
    @Query(value="select F.codfactura, F.fecha, F.idcliente, C.nomcliente, " +
    "C.telcliente" +
    " from cliente C inner join factura F on C.idcliente=F.idcliente " +
    " where F.idcliente=:idcliente",nativeQuery = true)

    // Metodo para ejecutar la consulta anterior
    List<FacturasClienteDTO>  buscarFacturasCliente(@Param("idcliente") String idcliente);
}
