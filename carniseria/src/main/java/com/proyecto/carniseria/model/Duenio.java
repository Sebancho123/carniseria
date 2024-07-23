
package com.proyecto.carniseria.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@Entity
public class Duenio {
    
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    private String nombre;
    private String apellido;
    private double dinero;

    public Duenio() {
    }

    public Duenio(Long id, String nombre, String apellido, double dinero) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.dinero = dinero;
    }
    
}
