package com.biblioteca.carlos.interfacs;

import com.biblioteca.carlos.model.Prestamo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IPrestamoRepository extends JpaRepository<Prestamo, Long> {
}