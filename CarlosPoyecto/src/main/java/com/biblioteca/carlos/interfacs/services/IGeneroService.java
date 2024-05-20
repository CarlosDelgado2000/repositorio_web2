package com.biblioteca.carlos.interfacs.services;

import com.biblioteca.carlos.model.Genero;

import java.util.List;
import java.util.Optional;

public interface IGeneroService {

    public List<Genero> list();
    public Optional<Genero> listId(Long id);
    public Genero save(Genero p);
    public Genero update(Long id, Genero newUser);
    public boolean delete(Long userId);


}