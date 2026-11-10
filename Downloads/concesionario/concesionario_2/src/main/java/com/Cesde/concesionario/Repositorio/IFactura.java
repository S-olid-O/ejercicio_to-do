package com.Cesde.concesionario.Repositorio;

import com.Cesde.concesionario.Dto.FacturasClienteDTO;
import com.Cesde.concesionario.Modelo.MFactura;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.time.LocalDate;
import java.util.List;

@Repository
public interface IFactura extends JpaRepository<MFactura,Integer> {
    // Busqueda por fecha
    List<MFactura> findByFecha (LocalDate Fecha);
    // Busqueda de las facturas de un cliente
    List<MFactura> findByIdcliente (String idCliente);
    // Consulta completa de facturas por cliente
    @Query(value= """
        Select
            C.nomcliente AS nomcliente,
            C.telcliente AS telcliente,
            F.codfactura AS codfactura,
            F.fecha AS fecha,
            F.idcliente AS idcliente,
            VF.placa AS placa,
            VF.valventa AS valventa,
            V.marca AS marca,
            V.modelo As modelo
       from cliente C inner join factura F on C.idcliente=F.idcliente
       inner join vehiculofactura VF on F.codfactura=VF.codfactura
       inner join vehiculo V on VF.placa=V.placa
       where F.idcliente = :idcliente""",nativeQuery = true)

    // Metodo para ejecutar la consulta anterior
    List<IFacturasCompletasProjection>  buscarFacturasCliente(@Param("idcliente") String idcliente);

}
