package com.biblioteca.carlos.interfacs;

import com.biblioteca.carlos.model.Devolucion;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IDevolucionRepository extends JpaRepository<Devolucion, Long> {
}