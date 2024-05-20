package com.biblioteca.carlos.controller;

import com.biblioteca.carlos.interfacs.services.IDevolucionService;
import com.biblioteca.carlos.model.Devolucion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/devoluciones")
public class DevolucionController {

    @Autowired
    private IDevolucionService devolucionService;

    @GetMapping
    public ResponseEntity<List<Devolucion>> getAllDevoluciones() {
        List<Devolucion> devolucionList = devolucionService.list();
        return ResponseEntity.ok().body(devolucionList);
    }

    @PostMapping
    public ResponseEntity<Devolucion> saveDevolucion(@Validated @RequestBody Devolucion devolucion) {
        Devolucion savedDevolucion = devolucionService.save(devolucion);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedDevolucion);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Devolucion>> getDevolucionById(@PathVariable("id") Long id) {
        Optional<Devolucion> devolucion = devolucionService.listId(id);
        if (devolucion.isPresent()) {
            return ResponseEntity.ok().body(devolucion);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Devolucion> updateDevolucion(@RequestBody Devolucion devolucion, @PathVariable("id") Long id) {
        Devolucion updatedDevolucion = devolucionService.update(id, devolucion);
        return ResponseEntity.ok().body(updatedDevolucion);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteDevolucionById(@PathVariable("id") Long id) {
        boolean deleted = devolucionService.delete(id);
        if (deleted) {
            return ResponseEntity.ok().body("Devolucion con id " + id + " ha sido eliminado exitosamente.");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Devolucion con id " + id + " no fue encontrado.");
        }
    }
}
