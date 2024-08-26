package PruebaT.ecommerce.controller.orden;

import PruebaT.ecommerce.dto.DetalleOrdenDTO;
import PruebaT.ecommerce.dto.OrdenDTO;
import PruebaT.ecommerce.service.implement.OrdenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
//http://localhost:8080/api-app
@RequestMapping("api-app")
@CrossOrigin(value = "http://localhost:4200")
public class OrdenController {
    @Autowired
    private OrdenService ordenService;

    @GetMapping("/orden")
    public List<OrdenDTO> listarOrdenes() {
        return ordenService.listarOrden();
    }

    @GetMapping("/orden/{id}")
    public ResponseEntity<OrdenDTO> obtenerOrdenPorId(@PathVariable int id) {
        OrdenDTO orden = ordenService.buscarOrdenId(id);
        return ResponseEntity.ok(orden);
    }

    @PostMapping("/orden")
    public ResponseEntity<?> crearOrden(@RequestBody List<DetalleOrdenDTO> detallesOrden) {
        try {
            OrdenDTO nuevaOrden = ordenService.crearOrden(detallesOrden);
            return ResponseEntity.status(HttpStatus.CREATED).body(nuevaOrden);
        } catch (ResponseStatusException e) {
            return ResponseEntity.status(e.getStatusCode()).body(e.getReason());
        }
    }

    @DeleteMapping("/orden/{id}")
    public ResponseEntity<Void> eliminarOrden(@PathVariable int id) {
        ordenService.eliminarOrden(id);
        return ResponseEntity.noContent().build();
    }

}
