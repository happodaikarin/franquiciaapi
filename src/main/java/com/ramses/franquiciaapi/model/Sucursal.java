/**
 * Representa una entidad 'Sucursal' en el sistema, vinculada a una 'Franquicia'.
 * 
 * - Usa JPA para mapear la entidad a una base de datos.
 * - Relación @ManyToOne con 'Franquicia', indicando que cada sucursal pertenece a una franquicia.
 * - Relación @OneToMany con 'Producto', gestionando los productos de la sucursal con cascada y eliminación de huérfanos.
 * - Las anotaciones de Jackson (@JsonBackReference, @JsonManagedReference) evitan referencias circulares en la serialización JSON.
 * - Lombok simplifica el código generando getters, setters y constructores.
 */
package com.ramses.franquiciaapi.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Sucursal {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;

    @ManyToOne
    @JoinColumn(name = "franquicia_id", nullable = false)
    @JsonBackReference
    private Franquicia franquicia;

    @OneToMany(mappedBy = "sucursal", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<Producto> productos;
}