/**
 * Representa una entidad 'Producto' en el sistema, vinculada a una 'Sucursal'.
 * 
 * - Usa JPA para mapear la entidad a una base de datos.
 * - Relación @ManyToOne con 'Sucursal', indicando que cada producto pertenece a una sucursal.
 * - La anotación @JsonBackReference evita referencias circulares en la serialización JSON.
 * - Lombok genera automáticamente getters, setters y constructores.
 */

package com.ramses.franquiciaapi.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Producto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;

    private Integer stock;

    @ManyToOne
    @JoinColumn(name = "sucursal_id", nullable = false)
    @JsonBackReference
    private Sucursal sucursal;
}