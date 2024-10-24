package com.ramses.franquiciaapi.repository;

import com.ramses.franquiciaapi.model.Producto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductoRepository extends JpaRepository<Producto, Long> {
    List<Producto> findBySucursalId(Long sucursalId);
}
