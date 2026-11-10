package com.Cesde.concesonario.Repositorio;

import com.Cesde.concesonario.Modelo.MFactura;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.time.LocalDate;
import java.util.List;

@Repository
public interface IFactura extends JpaRepository<MFactura,Integer> {
    // Consulta por fecha
    List<MFactura> findByFecha(LocalDate fecha);

    // Consulta por idCliente
    List<MFactura> findByIdcliente(String idcliente);

    // Consulta completa de las facturas de un cliente
    @Query(value="""
           SELECT
            C.nomcliente AS nomcliente,
            C.telcliente As telcliente,
            F.codfactura AS codfactura,
            F.fecha AS fecha,
            F.idcliente AS idcliente,
            VF.placa AS placa,
            VF.valventa AS valventa,
            V.marca AS marca,
            V.modelo AS modelo
       from cliente C inner join factura F on C.idcliente=F.idcliente
       inner join vehiculofactura VF on F.codfactura=VF.codfactura
       inner join vehiculo V on VF.placa=V.placa
       where F.idcliente = :idcliente""",nativeQuery = true)

    List<IFacturasCompletasClienteProjecion> consultaCompletaFacturaCliente(@Param("idcliente") String idcliente);
}
