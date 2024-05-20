package com.biblioteca.carlos.services;

import com.biblioteca.carlos.interfacs.ILibroRepository;
import com.biblioteca.carlos.interfacs.IPrestamoRepository;
import com.biblioteca.carlos.interfacs.IUsuarioRepository;
import com.biblioteca.carlos.interfacs.services.IPrestamoService;
import com.biblioteca.carlos.model.Prestamo;
import com.biblioteca.carlos.validator.Utils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PrestamoService implements IPrestamoService {

    private static final Logger LOGGER = LoggerFactory.getLogger(PrestamoService.class);

    @Autowired
    private IPrestamoRepository prestamoRepository;

    @Autowired
    private ILibroRepository libroRepository;

    @Autowired
    private IUsuarioRepository usuarioRepository;

    @Override
    public List<Prestamo> list() {
        try {
            return prestamoRepository.findAll();
        } catch (Exception e) {
            LOGGER.error("Error al obtener todos los préstamos: {}", e.getMessage());
            throw new RuntimeException("Error al obtener préstamos");
        }
    }

    @Override
    public Optional<Prestamo> listId(Long id) {
        try {
            Optional<Prestamo> prestamo = prestamoRepository.findById(id);
            if (prestamo.isPresent()) {
                return prestamo;
            } else {
                throw new RuntimeException("El préstamo con el ID especificado no se encontró en la base de datos.");
            }
        } catch (Exception e) {
            LOGGER.error("Error al obtener préstamo por ID: {}", e.getMessage());
            throw new RuntimeException("No se pudo obtener el préstamo. Por favor, asegúrese de proporcionar un ID válido.");
        }
    }

    @Override
    public Prestamo save(Prestamo prestamo) {
        if (prestamo.getLibro() == null) {
            throw new IllegalArgumentException("El libro es obligatorio para guardar el préstamo.");
        }

        if (!Utils.esFechaValida(prestamo.getFechaPrestamo())) {
            throw new IllegalArgumentException("La fecha de préstamo no es válida.");
        }

        if (!Utils.esFechaValida(prestamo.getFechaVencimiento())) {
            throw new IllegalArgumentException("La fecha de vencimiento no es válida.");
        }
        try {
        return prestamoRepository.save(prestamo);
        } catch (Exception e) {
            LOGGER.error("Error al guardar usuario: {}", e.getMessage());
            throw new RuntimeException("Error al guardar usuario");
        }
    }


    @Override
    public Prestamo update(Long id, Prestamo newPrestamo) {
        try {
            Optional<Prestamo> existingPrestamoOptional = prestamoRepository.findById(id);
            if (existingPrestamoOptional.isPresent()) {
                Prestamo existingPrestamo = existingPrestamoOptional.get();

                if (!Utils.esLibroValido(newPrestamo.getLibro())) {
                    throw new IllegalArgumentException("El libro no es válido");
                }

                if (!Utils.esUsuarioValido(newPrestamo.getUsuario())) {
                    throw new IllegalArgumentException("El usuario no es válido");
                }

                if (!Utils.esFechaValida(newPrestamo.getFechaPrestamo())) {
                    throw new IllegalArgumentException("La fecha de préstamo no es válida");
                }

                if (!Utils.esFechaValida(newPrestamo.getFechaVencimiento())) {
                    throw new IllegalArgumentException("La fecha de vencimiento no es válida");
                }

                existingPrestamo.setId(newPrestamo.getId());
                existingPrestamo.setLibro(newPrestamo.getLibro());
                existingPrestamo.setUsuario(newPrestamo.getUsuario());
                existingPrestamo.setFechaPrestamo(newPrestamo.getFechaPrestamo());
                existingPrestamo.setFechaVencimiento(newPrestamo.getFechaVencimiento());

                return prestamoRepository.save(existingPrestamo);
            } else {
                throw new RuntimeException("Préstamo no encontrado");
            }
        } catch (Exception e) {
            LOGGER.error("Error al actualizar préstamo: {}", e.getMessage());
            throw new RuntimeException("Error al actualizar préstamo");
        }
    }

    @Override
    public boolean delete(Long id) {
        try {
            Optional<Prestamo> prestamo = prestamoRepository.findById(id);
            if (prestamo.isPresent()) {
                prestamoRepository.deleteById(id);
                return true;
            } else {
                throw new RuntimeException("El préstamo que intenta eliminar no se encontró en la base de datos.");
            }
        } catch (Exception e) {
            LOGGER.error("Error al eliminar préstamo: {}", e.getMessage());
            throw new RuntimeException("No se pudo eliminar el préstamo en este momento. Por favor, inténtelo de nuevo más tarde.");
        }
    }
}
