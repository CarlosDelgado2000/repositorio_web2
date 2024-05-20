package com.biblioteca.carlos.interfacs.services;

import com.biblioteca.carlos.model.Devolucion;

import java.util.List;
import java.util.Optional;

public interface IDevolucionService {

    public List<Devolucion> list();
    public Optional<Devolucion> listId(Long id);
    public Devolucion save(Devolucion p);
    public Devolucion update(Long id, Devolucion newUser);
    public boolean delete(Long userId);


}