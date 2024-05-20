package com.biblioteca.carlos.model;

import jakarta.persistence.*;

@Entity
public class Genero {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer genero_id;
    private String nombre;


    public Genero(){}
    public Genero(Integer id,String nombre) {
        this.genero_id = id;
        this.nombre = nombre;
    }

    public Integer getId() {
        return genero_id;
    }

    public void setId(Integer id) {
        this.genero_id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
