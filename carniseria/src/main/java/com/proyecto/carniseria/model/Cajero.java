
package com.proyecto.carniseria.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter 
@Entity 
public class Cajero {
    
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int id;
    private String nombre;
    private double total_recaudado;
    private double sueldo_deCajero;

    public Cajero() {
    }

    public Cajero(int id, String nombre, double total_recaudado, double sueldo_deCajero) {
        this.id = id;
        this.nombre = nombre;
        this.total_recaudado = total_recaudado;
        this.sueldo_deCajero = sueldo_deCajero;
    }
}
