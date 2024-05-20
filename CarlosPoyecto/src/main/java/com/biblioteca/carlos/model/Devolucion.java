package com.biblioteca.carlos.model;

import jakarta.persistence.*;

import java.util.Date;

@Entity
public class Devolucion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Temporal(TemporalType.DATE)
    private Date fechaDevolucion;
    @ManyToOne
    @JoinColumn(name = "prestamo_id")
    private Prestamo prestamo;

    public Devolucion(){}
    public Devolucion(Integer id,Prestamo prestamo, Date fechaDevolucion) {
        this.id = id;
        this.prestamo = prestamo;
        this.fechaDevolucion = fechaDevolucion;
    }
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Prestamo getPrestamo() {
        return prestamo;
    }

    public void setPrestamo(Prestamo prestamo) {
        this.prestamo = prestamo;
    }

    public Date getFechaDevolucion() {
        return fechaDevolucion;
    }

    public void setFechaDevolucion(Date fechaDevolucion) {
        this.fechaDevolucion = fechaDevolucion;
    }
}