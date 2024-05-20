package com.biblioteca.carlos.interfacs.services;

import com.biblioteca.carlos.model.Libro;

import java.util.List;
import java.util.Optional;

public interface ILibroService {

    public List<Libro> list();
    public Optional<Libro> listId(Long id);
    public Libro save(Libro p);
    public Libro update(Long id, Libro newUser);
    public boolean delete(Long userId);


}