package com.proyecto.carniseria.model;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Convert;
import jakarta.persistence.Converter;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import java.io.IOException;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@Entity
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    private String nombre;
    private String apellido;
    private String dirreccion;
    private String atendido;
    private double sueldo;

    @Convert(converter = ListToJsonConverter.class)
    private List<Integer> listaComida;

    @OneToOne
    private Repartidor repartidor;

    public Cliente() {
    }

    public Cliente(Long id, String nombre, String apellido, String dirreccion, String atendido, double sueldo, List<Integer> listaComida, Repartidor repartidor) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.dirreccion = dirreccion;
        this.atendido = atendido;
        this.sueldo = sueldo;
        this.listaComida = listaComida;
        this.repartidor = repartidor;
    }
}

@Converter
class ListToJsonConverter implements AttributeConverter<List<Integer>, String> {

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public String convertToDatabaseColumn(List<Integer> attribute) {
        try {
            return objectMapper.writeValueAsString(attribute);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Error converting list to JSON", e);
        }
    }

    @Override
    public List<Integer> convertToEntityAttribute(String dbData) {
        try {
            return objectMapper.readValue(dbData, List.class);
        } catch (IOException e) {
            throw new RuntimeException("Error converting JSON to list", e);
        }
    }

}
