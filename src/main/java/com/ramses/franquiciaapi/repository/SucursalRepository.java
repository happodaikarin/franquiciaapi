package com.ramses.franquiciaapi.repository;

import com.ramses.franquiciaapi.model.Sucursal;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SucursalRepository extends JpaRepository<Sucursal, Long> {
}