package com.biblioteca.carlos.services;

import com.biblioteca.carlos.interfacs.ILibroRepository;
import com.biblioteca.carlos.interfacs.services.ILibroService;
import com.biblioteca.carlos.model.Libro;
import com.biblioteca.carlos.validator.Utils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LibroService implements ILibroService {

    private static final Logger LOGGER = LoggerFactory.getLogger(LibroService.class);

    @Autowired
    private ILibroRepository libroRepository;

    @Override
    public List<Libro> list() {
        try {
            return libroRepository.findAll();
        } catch (Exception e) {
            LOGGER.error("Error al obtener todos los libros: {}", e.getMessage());
            throw new RuntimeException("Error al obtener libros");
        }
    }

    @Override
    public Optional<Libro> listId(Long id) {
        try {
            Optional<Libro> libro = libroRepository.findById(id);
            if (libro.isPresent()) {
                return libro;
            } else {
                throw new RuntimeException("El libro con el ID especificado no se encontró en la base de datos.");
            }
        } catch (Exception e) {
            LOGGER.error("Error al obtener libro por ID: {}", e.getMessage());
            throw new RuntimeException("No se pudo obtener el libro. Por favor, asegúrese de proporcionar un ID válido.");
        }
    }
    @Override
    public Libro save(Libro libro) {
        if (libro.getTitulo() == null || libro.getTitulo().isEmpty()) {
            throw new IllegalArgumentException("El título del libro es obligatorio.");
        }
        if (libro.getAutor() == null || libro.getAutor().isEmpty()) {
            throw new IllegalArgumentException("El autor del libro es obligatorio.");
        }
        if (libro.getAnoPublicacion() == null || libro.getAnoPublicacion() <= 0) {
            throw new IllegalArgumentException("El año de publicación del libro debe ser mayor que 0.");
        }
        if (libro.getNumEjemplares() == null || libro.getNumEjemplares() < 0) {
            throw new IllegalArgumentException("El número de ejemplares del libro debe ser mayor o igual que 0.");
        }

        try {
            return libroRepository.save(libro);
        } catch (Exception e) {
            LOGGER.error("Error al guardar libro: {}", e.getMessage());
            throw new RuntimeException("Error al guardar libro");
        }
    }


    @Override
    public Libro update(Long id, Libro newLibro) {
        try {
            Optional<Libro> existingLibroOptional = libroRepository.findById(id);
            if (existingLibroOptional.isPresent()) {
                Libro existingLibro = existingLibroOptional.get();


                if (!Utils.esTextoValido(newLibro.getTitulo())) {
                    throw new IllegalArgumentException("El título del libro no es válido");
                }

                if (!Utils.esTextoValido(newLibro.getAutor())) {
                    throw new IllegalArgumentException("El autor del libro no es válido");
                }

                if (!Utils.esAnoValido(newLibro.getAnoPublicacion())) {
                    throw new IllegalArgumentException("El año de publicación del libro no es válido");
                }

                if (!Utils.esNumeroValido(newLibro.getNumEjemplares())) {
                    throw new IllegalArgumentException("El número de ejemplares del libro debe ser un número válido");
                }

                if (!Utils.esGeneroValido(newLibro.getGenero())) {
                    throw new IllegalArgumentException("El género del libro no es válido");
                }

                if (!Utils.esEditorialValido(newLibro.getEditorial())) {
                    throw new IllegalArgumentException("La editorial del libro no es válida");
                }

                // Actualizar los campos del libro existente con los nuevos datos
                existingLibro.setId(newLibro.getId());
                existingLibro.setTitulo(newLibro.getTitulo());
                existingLibro.setAutor(newLibro.getAutor());
                existingLibro.setAnoPublicacion(newLibro.getAnoPublicacion());
                existingLibro.setNumEjemplares(newLibro.getNumEjemplares());
                existingLibro.setGenero(newLibro.getGenero());
                existingLibro.setEditorial(newLibro.getEditorial());

                // Guardar y devolver el libro actualizado
                return libroRepository.save(existingLibro);
            } else {
                // Si el libro no existe, lanzar una excepción
                throw new RuntimeException("Libro no encontrado");
            }
        } catch (Exception e) {
            // Manejar cualquier error que pueda ocurrir durante la actualización
            LOGGER.error("Error al actualizar libro: {}", e.getMessage());
            throw new RuntimeException("Error al actualizar libro");
        }
    }

    @Override
    public boolean delete(Long id) {
        try {
            Optional<Libro> libro = libroRepository.findById(id);
            if (libro.isPresent()) {
                libroRepository.deleteById(id);
                return true;
            } else {
                throw new RuntimeException("El libro que intenta eliminar no se encontró en la base de datos.");
            }
        } catch (Exception e) {
            LOGGER.error("Error al eliminar libro: {}", e.getMessage());
            throw new RuntimeException("No se pudo eliminar el libro en este momento. Por favor, inténtelo de nuevo más tarde.");
        }
    }
}
