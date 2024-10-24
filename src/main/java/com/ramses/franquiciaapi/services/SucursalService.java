package com.ramses.franquiciaapi.services;

import com.ramses.franquiciaapi.model.Franquicia;
import com.ramses.franquiciaapi.model.Sucursal;
import com.ramses.franquiciaapi.repository.SucursalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class SucursalService {

    @Autowired
    private SucursalRepository sucursalRepository;

    @Autowired
    private FranquiciaService franquiciaService;

    public Sucursal agregarSucursal(Long franquiciaId, Sucursal sucursal) throws Exception {
        Franquicia franquicia = franquiciaService.obtenerFranquiciaPorId(franquiciaId)
                .orElseThrow(() -> new Exception("Franquicia no encontrada"));
        sucursal.setFranquicia(franquicia);
        return sucursalRepository.save(sucursal);
    }

    public Optional<Sucursal> obtenerSucursalPorId(Long id) {
        return sucursalRepository.findById(id);
    }

    public Sucursal actualizarNombre(Long id, String nuevoNombre) throws Exception {
        Sucursal sucursal = sucursalRepository.findById(id)
                .orElseThrow(() -> new Exception("Sucursal no encontrada"));
        sucursal.setNombre(nuevoNombre);
        return sucursalRepository.save(sucursal);
    }
}
