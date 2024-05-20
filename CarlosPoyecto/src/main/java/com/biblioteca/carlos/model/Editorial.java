package com.biblioteca.carlos.model;
import jakarta.persistence.*;

@Entity
public class Editorial {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer editorial_id;
    private String nombre;

    public Editorial(){}

    public Editorial(Integer id, String nombre) {
        this.editorial_id = id;
        this.nombre = nombre;
    }

    public Integer getId() {
        return editorial_id;
    }

    public void setId(Integer id) {
        this.editorial_id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}