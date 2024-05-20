package com.biblioteca.carlos.interfacs.services;

import com.biblioteca.carlos.model.Prestamo;

import java.util.List;
import java.util.Optional;

public interface IPrestamoService {

    public List<Prestamo> list();
    public Optional<Prestamo> listId(Long id);
    public Prestamo save(Prestamo p);
    public Prestamo update(Long id, Prestamo newUser);
    public boolean delete(Long userId);


}