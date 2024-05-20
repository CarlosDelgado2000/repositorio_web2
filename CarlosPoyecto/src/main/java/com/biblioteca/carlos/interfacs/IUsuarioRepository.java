package com.biblioteca.carlos.interfacs;

import com.biblioteca.carlos.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IUsuarioRepository extends JpaRepository<Usuario, Long> {
}