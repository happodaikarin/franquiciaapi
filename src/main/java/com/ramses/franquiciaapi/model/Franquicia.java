/**
 * Representa una entidad 'Franquicia' en el sistema, que gestiona varias 'Sucursales'.
 * 
 * - Usa JPA para mapear la entidad a una base de datos.
 * - Relación @OneToMany con 'Sucursal', gestionando las sucursales con cascada y eliminación de huérfanos.
 * - La anotación @JsonManagedReference permite la correcta serialización JSON de las sucursales.
 * - Lombok genera automáticamente getters, setters y constructores.
 */

package com.ramses.franquiciaapi.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Franquicia {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;

    @OneToMany(mappedBy = "franquicia", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<Sucursal> sucursales;
}