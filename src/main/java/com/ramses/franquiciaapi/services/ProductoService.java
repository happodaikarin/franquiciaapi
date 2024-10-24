package com.ramses.franquiciaapi.services;

import com.ramses.franquiciaapi.model.Producto;
import com.ramses.franquiciaapi.model.Sucursal;
import com.ramses.franquiciaapi.repository.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductoService {

    @Autowired
    private ProductoRepository productoRepository;

    @Autowired
    private SucursalService sucursalService;

    public Producto agregarProducto(Long sucursalId, Producto producto) throws Exception {
        // Verificar que la sucursal exista y obtenerla
        Sucursal sucursal = sucursalService.obtenerSucursalPorId(sucursalId)
                .orElseThrow(() -> new Exception("Sucursal no encontrada"));

        // Establecer la sucursal en el producto
        producto.setSucursal(sucursal);

        // Guardar el producto en la base de datos
        return productoRepository.save(producto);
    }

    public void eliminarProducto(Long productoId) throws Exception {
        Producto producto = productoRepository.findById(productoId)
                .orElseThrow(() -> new Exception("Producto no encontrado"));
        productoRepository.delete(producto);
    }

    public Producto modificarStock(Long productoId, Integer nuevoStock) throws Exception {
        Producto producto = productoRepository.findById(productoId)
                .orElseThrow(() -> new Exception("Producto no encontrado"));
        producto.setStock(nuevoStock);
        return productoRepository.save(producto);
    }

    public List<Producto> obtenerProductosPorSucursal(Long sucursalId) {
        return productoRepository.findBySucursalId(sucursalId);
    }

    public Producto actualizarNombre(Long id, String nuevoNombre) throws Exception {
        Producto producto = productoRepository.findById(id)
                .orElseThrow(() -> new Exception("Producto no encontrado"));
        producto.setNombre(nuevoNombre);
        return productoRepository.save(producto);
    }
}
