package com.biblioteca.carlos.model;

import jakarta.persistence.*;
@Entity
public class Libro {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer libro_id;
    private String titulo;
    private String autor;
    private Integer anoPublicacion;
    private Integer numEjemplares;

    @ManyToOne
    @JoinColumn(name = "genero_id")
    private Genero genero;

    @ManyToOne
    @JoinColumn(name = "editorial_id")
    private Editorial editorial;

    public Libro(){}

    public Libro(Integer id,String titulo, String autor, Integer anoPublicacion, Integer numEjemplares, Genero genero, Editorial editorial) {
        this.libro_id = id;
        this.titulo = titulo;
        this.autor = autor;
        this.anoPublicacion = anoPublicacion;
        this.numEjemplares = numEjemplares;
        this.genero = genero;
        this.editorial = editorial;
    }
    public Integer getId() {
        return libro_id;
    }

    public void setId(Integer id) {
        this.libro_id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public Integer getAnoPublicacion() {
        return anoPublicacion;
    }

    public void setAnoPublicacion(Integer anoPublicacion) {
        this.anoPublicacion = anoPublicacion;
    }

    public Integer getNumEjemplares() {
        return numEjemplares;
    }

    public void setNumEjemplares(Integer numEjemplares) {
        this.numEjemplares = numEjemplares;
    }

    public Genero getGenero() {
        return genero;
    }

    public void setGenero(Genero genero) {
        this.genero = genero;
    }

    public Editorial getEditorial() {
        return editorial;
    }

    public void setEditorial(Editorial editorial) {
        this.editorial = editorial;
    }
}