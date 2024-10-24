/**
 * Controlador REST para gestionar franquicias, sucursales y productos.
 * 
 * - Proporciona endpoints para CRUD de franquicias, sucursales y productos.
 * - Usa servicios (FranquiciaService, SucursalService, ProductoService) para la lógica de negocio.
 * - Maneja relaciones entre franquicias, sucursales y productos.
 * - Utiliza ResponseEntity para manejar respuestas HTTP de manera clara.
 */

package com.ramses.franquiciaapi.controller;


import com.ramses.franquiciaapi.model.Franquicia;
import com.ramses.franquiciaapi.model.Producto;
import com.ramses.franquiciaapi.model.Sucursal;
import com.ramses.franquiciaapi.services.FranquiciaService;
import com.ramses.franquiciaapi.services.ProductoService;
import com.ramses.franquiciaapi.services.SucursalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.*;

@RestController
@RequestMapping("/api/franquicias")
public class FranquiciaController {

    @Autowired
    private FranquiciaService franquiciaService;

    @Autowired
    private SucursalService sucursalService;

    @Autowired
    private ProductoService productoService;

    // Endpoint para agregar una nueva franquicia
    @PostMapping
    public ResponseEntity<Franquicia> agregarFranquicia(@RequestBody Franquicia franquicia) {
        Franquicia creada = franquiciaService.agregarFranquicia(franquicia);
        return ResponseEntity.ok(creada);
    }

    // Endpoint para actualizar el nombre de una franquicia (Punto extra)
    @PutMapping("/{id}/nombre")
    public ResponseEntity<?> actualizarNombreFranquicia(@PathVariable Long id, @RequestBody Map<String, String> body) {
        try {
            String nuevoNombre = body.get("nombre");
            Franquicia actualizada = franquiciaService.actualizarNombre(id, nuevoNombre);
            return ResponseEntity.ok(actualizada);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    // Endpoint para agregar una nueva sucursal a una franquicia
    @PostMapping("/{franquiciaId}/sucursales")
    public ResponseEntity<?> agregarSucursal(@PathVariable Long franquiciaId, @RequestBody Map<String, String> body) {
        try {
            String nombreSucursal = body.get("nombre");
            Sucursal sucursal = new Sucursal();
            sucursal.setNombre(nombreSucursal);
            Sucursal creada = sucursalService.agregarSucursal(franquiciaId, sucursal);
            return ResponseEntity.ok(creada);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    // Endpoint para actualizar el nombre de una sucursal (Punto extra)
    @PutMapping("/sucursales/{id}/nombre")
    public ResponseEntity<?> actualizarNombreSucursal(@PathVariable Long id, @RequestBody Map<String, String> body) {
        try {
            String nuevoNombre = body.get("nombre");
            Sucursal actualizada = sucursalService.actualizarNombre(id, nuevoNombre);
            return ResponseEntity.ok(actualizada);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/{franquiciaId}/sucursales/{sucursalId}/productos")
public ResponseEntity<?> agregarProducto(@PathVariable Long franquiciaId, @PathVariable Long sucursalId, @RequestBody Producto producto) {
    try {
        Producto creado = productoService.agregarProducto(sucursalId, producto);
        return ResponseEntity.ok(creado);
    } catch (Exception e) {
        return ResponseEntity.badRequest().body(e.getMessage());
    }
}

    

    // Endpoint para eliminar un producto de una sucursal
    @DeleteMapping("/productos/{productoId}")
    public ResponseEntity<?> eliminarProducto(@PathVariable Long productoId) {
        try {
            productoService.eliminarProducto(productoId);
            return ResponseEntity.ok("Producto eliminado exitosamente");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    // Endpoint para modificar el stock de un producto
    @PutMapping("/productos/{productoId}/stock")
    public ResponseEntity<?> modificarStock(@PathVariable Long productoId, @RequestBody Map<String, Integer> body) {
        try {
            Integer nuevoStock = body.get("stock");
            Producto actualizado = productoService.modificarStock(productoId, nuevoStock);
            return ResponseEntity.ok(actualizado);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    // Endpoint para actualizar el nombre de un producto (Punto extra)
    @PutMapping("/productos/{productoId}/nombre")
    public ResponseEntity<?> actualizarNombreProducto(@PathVariable Long productoId, @RequestBody Map<String, String> body) {
        try {
            String nuevoNombre = body.get("nombre");
            Producto actualizado = productoService.actualizarNombre(productoId, nuevoNombre);
            return ResponseEntity.ok(actualizado);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    // Endpoint para mostrar el producto con más stock por sucursal para una franquicia puntual
    @GetMapping("/{franquiciaId}/productos-mas-stock")
    public ResponseEntity<?> obtenerProductosConMasStock(@PathVariable Long franquiciaId) {
        try {
            Franquicia franquicia = franquiciaService.obtenerFranquiciaPorId(franquiciaId)
                    .orElseThrow(() -> new Exception("Franquicia no encontrada"));

            List<Map<String, Object>> resultado = new ArrayList<>();

            for (Sucursal sucursal : franquicia.getSucursales()) {
                Optional<Producto> productoMax = sucursal.getProductos().stream()
                        .max(Comparator.comparingInt(Producto::getStock));
                if (productoMax.isPresent()) {
                    Map<String, Object> productoInfo = new HashMap<>();
                    productoInfo.put("sucursal", sucursal.getNombre());
                    productoInfo.put("producto", productoMax.get().getNombre());
                    productoInfo.put("stock", productoMax.get().getStock());
                    resultado.add(productoInfo);
                }
            }

            return ResponseEntity.ok(resultado);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    // Endpoint para obtener una franquicia por ID
    @GetMapping("/{id}")
    public ResponseEntity<?> obtenerFranquicia(@PathVariable Long id) {
        Optional<Franquicia> franquicia = franquiciaService.obtenerFranquiciaPorId(id);
        if (franquicia.isPresent()) {
            return ResponseEntity.ok(franquicia.get());
        } else {
            return ResponseEntity.badRequest().body("Franquicia no encontrada");
        }
    }

    //endpoint para ver todas las franquicias
    @GetMapping
    public ResponseEntity<?> listarFranquicias() {
        Iterable<Franquicia> franquicias = franquiciaService.listarFranquicias();
        return ResponseEntity.ok(franquicias);
    }

    @GetMapping("/sucursales/{id}")
    public ResponseEntity<?> obtenerSucursal(@PathVariable Long id) {
        Optional<Sucursal> sucursal = sucursalService.obtenerSucursalPorId(id);
        if (sucursal.isPresent()) {
            return ResponseEntity.ok(sucursal.get());
        } else {
            return ResponseEntity.badRequest().body("sucursal no encontrada");
        }
    }
}
