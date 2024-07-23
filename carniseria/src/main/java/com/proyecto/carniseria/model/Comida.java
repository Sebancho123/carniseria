
package com.proyecto.carniseria.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@Entity
public class Comida {
    
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long codigo_comida;
    private String nombre;
    private String tipo;
    private double precio;
    private boolean disponible;
    private int cantidad;

    public Comida() {
    }

    public Comida(Long codigo_comida, String nombre, String tipo, double precio, boolean disponible, int cantidad) {
        this.codigo_comida = codigo_comida;
        this.nombre = nombre;
        this.tipo = tipo;
        this.precio = precio;
        this.disponible = disponible;
        this.cantidad = cantidad;
    }
}
