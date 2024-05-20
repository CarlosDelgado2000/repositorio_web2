package com.biblioteca.carlos.services;

import com.biblioteca.carlos.interfacs.IGeneroRepository;
import com.biblioteca.carlos.interfacs.services.IGeneroService;
import com.biblioteca.carlos.model.Genero;
import com.biblioteca.carlos.validator.Utils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GeneroService implements IGeneroService {

    private static final Logger LOGGER = LoggerFactory.getLogger(GeneroService.class);

    @Autowired
    private IGeneroRepository generoRepository;

    @Override
    public List<Genero> list() {
        try {
            return generoRepository.findAll();
        } catch (Exception e) {
            LOGGER.error("Error al obtener todos los géneros: {}", e.getMessage());
            throw new RuntimeException("Error al obtener géneros");
        }
    }

    @Override
    public Optional<Genero> listId(Long id) {
        try {
            Optional<Genero> genero = generoRepository.findById(id);
            if (genero.isPresent()) {
                return genero;
            } else {
                throw new RuntimeException("El género con el ID especificado no se encontró en la base de datos.");
            }
        } catch (Exception e) {
            LOGGER.error("Error al obtener género por ID: {}", e.getMessage());
            throw new RuntimeException("No se pudo obtener el género. Por favor, asegúrese de proporcionar un ID válido.");
        }
    }

    @Override
    public Genero save(Genero genero) {
        if (genero.getNombre() == null || genero.getNombre().isEmpty()) {
            throw new IllegalArgumentException("El nombre del género es obligatorio.");
        }
        try {
            return generoRepository.save(genero);
        } catch (Exception e) {
            LOGGER.error("Error al guardar género: {}", e.getMessage());
            throw new RuntimeException("Error al guardar género");
        }
    }

    @Override
    public Genero update(Long id, Genero newGenero) {
        try {
            Optional<Genero> existingGenero = generoRepository.findById(id);
            if (existingGenero.isPresent()) {
                Genero updatedGenero = existingGenero.get();

                if (!Utils.esNombreValido(newGenero.getNombre())) {
                    throw new IllegalArgumentException("El nombre no es válido");
                }

                updatedGenero.setId(newGenero.getId());
                updatedGenero.setNombre(newGenero.getNombre());

                return generoRepository.save(updatedGenero);
            }
            throw new RuntimeException("Género no encontrado");
        } catch (Exception e) {
            LOGGER.error("Error al actualizar género: {}", e.getMessage());
            throw new RuntimeException("Error al actualizar género");
        }
    }

    @Override
    public boolean delete(Long id) {
        try {
            Optional<Genero> genero = generoRepository.findById(id);
            if (genero.isPresent()) {
                generoRepository.deleteById(id);
                return true;
            } else {
                throw new RuntimeException("El género que intenta eliminar no se encontró en la base de datos.");
            }
        } catch (Exception e) {
            LOGGER.error("Error al eliminar género: {}", e.getMessage());
            throw new RuntimeException("No se pudo eliminar el género en este momento. Por favor, inténtelo de nuevo más tarde.");
        }
    }
}
