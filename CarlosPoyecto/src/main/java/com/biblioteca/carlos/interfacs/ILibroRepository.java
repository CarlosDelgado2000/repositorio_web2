package com.biblioteca.carlos.interfacs;

import com.biblioteca.carlos.model.Libro;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ILibroRepository extends JpaRepository<Libro, Long> {
}