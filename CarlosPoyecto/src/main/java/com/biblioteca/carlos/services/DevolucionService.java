package com.biblioteca.carlos.services;

import com.biblioteca.carlos.interfacs.IDevolucionRepository;
import com.biblioteca.carlos.interfacs.services.IDevolucionService;
import com.biblioteca.carlos.model.Devolucion;
import com.biblioteca.carlos.validator.Utils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DevolucionService implements IDevolucionService {

    private static final Logger LOGGER = LoggerFactory.getLogger(DevolucionService.class);

    @Autowired
    private IDevolucionRepository devolucionRepository;

    @Override
    public List<Devolucion> list() {
        try {
            return devolucionRepository.findAll();
        } catch (Exception e) {
            LOGGER.error("Error al obtener todas las devoluciones: {}", e.getMessage());
            throw new RuntimeException("Error al obtener devoluciones");
        }
    }

    @Override
    public Optional<Devolucion> listId(Long id) {
        try {
            Optional<Devolucion> devolucion = devolucionRepository.findById(id);
            if (devolucion.isPresent()) {
                return devolucion;
            } else {
                throw new RuntimeException("La devolución con el ID especificado no se encontró en la base de datos.");
            }
        } catch (Exception e) {
            LOGGER.error("Error al obtener devolución por ID: {}", e.getMessage());
            throw new RuntimeException("No se pudo obtener la devolución. Por favor, asegúrese de proporcionar un ID válido.");
        }
    }

    @Override
    public Devolucion save(Devolucion devolucion) {
        if (devolucion.getFechaDevolucion() == null) {
            throw new IllegalArgumentException("La fecha de devolución es obligatoria.");
        }
        try {
            return devolucionRepository.save(devolucion);
        } catch (Exception e) {
            LOGGER.error("Error al guardar devolución: {}", e.getMessage());
            throw new RuntimeException("Error al guardar devolución");
        }
    }


    @Override
    public Devolucion update(Long id, Devolucion newDevolucion) {
        try {
            Optional<Devolucion> existingDevolucion = devolucionRepository.findById(id);
            if (existingDevolucion.isPresent()) {
                Devolucion updatedDevolucion = existingDevolucion.get();

                if (!Utils.esPrestamoValido(newDevolucion.getPrestamo())) {
                    throw new IllegalArgumentException("El género del libro no es válido");
                }

                updatedDevolucion.setId(newDevolucion.getId());
                updatedDevolucion.setPrestamo(newDevolucion.getPrestamo());
                updatedDevolucion.setFechaDevolucion(newDevolucion.getFechaDevolucion());

                return devolucionRepository.save(updatedDevolucion);
            }
            throw new RuntimeException("Devolución no encontrada");
        } catch (Exception e) {
            LOGGER.error("Error al actualizar devolución: {}", e.getMessage());
            throw new RuntimeException("Error al actualizar devolución");
        }
    }

    @Override
    public boolean delete(Long id) {
        try {
            Optional<Devolucion> devolucion = devolucionRepository.findById(id);
            if (devolucion.isPresent()) {
                devolucionRepository.deleteById(id);
                return true;
            } else {
                throw new RuntimeException("La devolución que intenta eliminar no se encontró en la base de datos.");
            }
        } catch (Exception e) {
            LOGGER.error("Error al eliminar devolución: {}", e.getMessage());
            throw new RuntimeException("No se pudo eliminar la devolución en este momento. Por favor, inténtelo de nuevo más tarde.");
        }
    }
}
