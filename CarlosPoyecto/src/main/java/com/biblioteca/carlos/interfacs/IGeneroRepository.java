package com.biblioteca.carlos.interfacs;

import com.biblioteca.carlos.model.Genero;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IGeneroRepository extends JpaRepository<Genero, Long> {
}