package com.ramses.franquiciaapi.repository;

import com.ramses.franquiciaapi.model.Franquicia;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FranquiciaRepository extends JpaRepository<Franquicia, Long> {
}