package com.Cesde.concesionario.Controlador;

import com.Cesde.concesionario.Modelo.MCliente;
import com.Cesde.concesionario.Servicio.SCliente;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/clientes")
@CrossOrigin(origins = "*")
@Tag(name = "Servicio cliente",description = "Clase para manejar las operaciones básicas de la entidad cliente")
public class CCliente {
    // Clase a instanciar
    @Autowired
    SCliente sCliente;

    // Adicionar un registro cliente
    @PostMapping
    @Operation(summary = "Adicion de registro cliente",
               description = "Adiciona un cliente con llave primaria identificacion")
    public ResponseEntity<?> adicionarCliente(@RequestBody MCliente mCliente) throws Exception {
        try {
            return ResponseEntity
                    .status(HttpStatus.CREATED)
                    .body(this.sCliente.adicionarCliente(mCliente));
        } catch (Exception error) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(error.getMessage());
        }
    }

    // Consulta general
    @GetMapping
    @Operation(summary = "Lista todos los clientes",
               description = "Muestra todos los datos de cada cliente")
    public ResponseEntity<?> consultaGeneralCli() throws Exception {
        try {
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(this.sCliente.consultaGeneralCli());
        } catch (Exception error) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(error.getMessage());
        }
    }

    // Consulta individual por llave primaria
    @GetMapping("/{idCliente}")
    @Operation(summary = "Consulta un cliente por llave primaria",
              description = "Busca el cliente por un campo texto de maximo 15 caracteres")
    public ResponseEntity<?> consultaIndividualId(@PathVariable String idCliente) throws Exception {
        try {
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(this.sCliente.consultaIndividualId(idCliente));
        } catch (Exception error) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(error.getMessage());
        }
    }

    // Consulta por el nombre del cliente
    @GetMapping("/nombre/{nomCliente}")
    @Operation(summary = "Consulta por nombre del cliente",
               description = "Si no se tiene la llave primaria se puede buscar por su nombre")
    public ResponseEntity<?> consultaIndividualNom(@PathVariable String nomCliente) throws Exception {
        try {
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(this.sCliente.consultaIndividualNom(nomCliente));
        } catch (Exception error) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(error.getMessage());
        }
    }

    // Modificar un registro cliente
    @PutMapping("/{idCliente}")
    @Operation(summary = "Modifica los datos de un cliente",
               description = "Se pueden modificar todos los campos excepto la llave primaria")
    public ResponseEntity<?> modificarCliente(@RequestBody MCliente mCliente,
                                              @PathVariable String idCliente) throws Exception {
        try {
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(this.sCliente.modificarCliente(mCliente, idCliente));
        } catch (Exception error) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(error.getMessage());
        }
    }

    // Eliminar un registro cliente
    @DeleteMapping("/{idCliente}")
    @Operation(summary = "Saca un registro fisicamente de la tabla cliente",
               description = "Recuerde que no se puede eliminar si tiene registros relacionados")
    public ResponseEntity<?> eliminarCliente(@PathVariable String idCliente) throws Exception{
            try{
                return ResponseEntity
                        .status(HttpStatus.OK)
                        .body(this.sCliente.eliminarCliente(idCliente));
            }catch (Exception error) {
                return ResponseEntity
                        .status(HttpStatus.BAD_REQUEST)
                        .body(error.getMessage());
            }
        }
}
