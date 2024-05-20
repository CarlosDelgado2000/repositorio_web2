package com.biblioteca.carlos.controller;

import com.biblioteca.carlos.interfacs.services.IPrestamoService;
import com.biblioteca.carlos.model.Prestamo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/prestamos")
public class PrestamoController {

    @Autowired
    private IPrestamoService prestamoService;

    @GetMapping
    public ResponseEntity<List<Prestamo>> getAllPrestamos() {
        List<Prestamo> prestamoList = prestamoService.list();
        return ResponseEntity.ok().body(prestamoList);
    }

    @PostMapping
    public ResponseEntity<Prestamo> savePrestamo(@Validated @RequestBody Prestamo prestamo) {
        Prestamo savedPrestamo = prestamoService.save(prestamo);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedPrestamo);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Prestamo>> getPrestamoById(@PathVariable("id") Long id) {
        Optional<Prestamo> prestamo = prestamoService.listId(id);
        if (prestamo.isPresent()) {
            return ResponseEntity.ok().body(prestamo);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Prestamo> updatePrestamo(@RequestBody Prestamo prestamo, @PathVariable("id") Long id) {
        Prestamo updatedPrestamo = prestamoService.update(id, prestamo);
        return ResponseEntity.ok().body(updatedPrestamo);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletePrestamoById(@PathVariable("id") Long id) {
        boolean deleted = prestamoService.delete(id);
        if (deleted) {
            return ResponseEntity.ok().body("Prestamo con id " + id + " ha sido eliminado exitosamente.");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Prestamo con id " + id + " no fue encontrado.");
        }
    }
}
