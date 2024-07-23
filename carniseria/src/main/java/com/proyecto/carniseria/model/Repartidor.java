
package com.proyecto.carniseria.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@Entity
public class Repartidor {
 
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    private String nombre;
    private String estado;
    private String localizacion_del_cliente;

    public Repartidor() {
    }

    public Repartidor(Long id, String nombre, String estado, String localizacion_del_cliente) {
        this.id = id;
        this.nombre = nombre;
        this.estado = estado;
        this.localizacion_del_cliente = localizacion_del_cliente;
    }
}
