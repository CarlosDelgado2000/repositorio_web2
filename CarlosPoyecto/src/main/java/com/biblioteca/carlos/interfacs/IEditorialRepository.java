package com.biblioteca.carlos.interfacs;

import com.biblioteca.carlos.model.Editorial;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IEditorialRepository extends JpaRepository<Editorial, Long> {
}