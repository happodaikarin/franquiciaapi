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