package com.biblioteca.carlos.services;

import com.biblioteca.carlos.interfacs.IEditorialRepository;
import com.biblioteca.carlos.interfacs.services.IEditorialService;
import com.biblioteca.carlos.model.Editorial;
import com.biblioteca.carlos.validator.Utils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EditorialService implements IEditorialService {

    private static final Logger LOGGER = LoggerFactory.getLogger(EditorialService.class);

    @Autowired
    private IEditorialRepository editorialRepository;

    @Override
    public List<Editorial> list() {
        try {
            return editorialRepository.findAll();
        } catch (Exception e) {
            LOGGER.error("Error al obtener todas las editoriales: {}", e.getMessage());
            throw new RuntimeException("Error al obtener editoriales");
        }
    }

    @Override
    public Optional<Editorial> listId(Long id) {
        try {
            Optional<Editorial> editorial = editorialRepository.findById(id);
            if (editorial.isPresent()) {
                return editorial;
            } else {
                throw new RuntimeException("La editorial con el ID especificado no se encontró en la base de datos.");
            }
        } catch (Exception e) {
            LOGGER.error("Error al obtener editorial por ID: {}", e.getMessage());
            throw new RuntimeException("No se pudo obtener la editorial. Por favor, asegúrese de proporcionar un ID válido.");
        }
    }

    @Override
    public Editorial save(Editorial editorial) {
        if (editorial.getNombre() == null || editorial.getNombre().isEmpty()) {
            throw new IllegalArgumentException("El nombre de la editorial es obligatorio.");
        }

        try {
            return editorialRepository.save(editorial);
        } catch (Exception e) {
            LOGGER.error("Error al guardar editorial: {}", e.getMessage());
            throw new RuntimeException("Error al guardar editorial");
        }
    }


    @Override
    public Editorial update(Long id, Editorial newEditorial) {
        try {
            Optional<Editorial> existingEditorial = editorialRepository.findById(id);
            if (existingEditorial.isPresent()) {
                Editorial updatedEditorial = existingEditorial.get();

                if (!Utils.esNombreValido(newEditorial.getNombre())) {
                    throw new IllegalArgumentException("El nombre no es válido");
                }

                updatedEditorial.setId(newEditorial.getId());
                updatedEditorial.setNombre(newEditorial.getNombre());

                return editorialRepository.save(updatedEditorial);
            }
            throw new RuntimeException("Editorial no encontrada");
        } catch (Exception e) {
            LOGGER.error("Error al actualizar editorial: {}", e.getMessage());
            throw new RuntimeException("Error al actualizar editorial");
        }
    }

    @Override
    public boolean delete(Long id) {
        try {
            Optional<Editorial> editorial = editorialRepository.findById(id);
            if (editorial.isPresent()) {
                editorialRepository.deleteById(id);
                return true;
            } else {
                throw new RuntimeException("La editorial que intenta eliminar no se encontró en la base de datos.");
            }
        } catch (Exception e) {
            LOGGER.error("Error al eliminar editorial: {}", e.getMessage());
            throw new RuntimeException("No se pudo eliminar la editorial en este momento. Por favor, inténtelo de nuevo más tarde.");
        }
    }
}
