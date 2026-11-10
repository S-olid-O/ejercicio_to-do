package com.Cesde.concesionario.Controlador;

import com.Cesde.concesionario.Servicio.SFactura;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/facturas")
@CrossOrigin(origins = "*")
public class CFactura {
    @Autowired
    SFactura sFactura;

    // Consulta facturas por cliente
    @GetMapping("/{idCliente}")
    public ResponseEntity<?> consultaFacturasId(@PathVariable String idCliente) throws Exception {
        try {
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(this.sFactura.obtenerFacturasCompletasDTO(idCliente));
        } catch (Exception error) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(error.getMessage());
        }
    }
}
