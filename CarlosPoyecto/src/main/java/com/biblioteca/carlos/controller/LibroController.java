package com.biblioteca.carlos.controller;

import com.biblioteca.carlos.interfacs.services.ILibroService;
import com.biblioteca.carlos.model.Libro;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/libros")
public class LibroController {

    @Autowired
    private ILibroService libroService;

    @GetMapping
    public ResponseEntity<List<Libro>> getAllLibros() {
        List<Libro> libroList = libroService.list();
        return ResponseEntity.ok().body(libroList);
    }

    @PostMapping
    public ResponseEntity<Libro> saveLibro(@Validated @RequestBody Libro libro) {
        Libro savedLibro = libroService.save(libro);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedLibro);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Libro>> getLibroById(@PathVariable("id") Long id) {
        Optional<Libro> libro = libroService.listId(id);
        if (libro.isPresent()) {
            return ResponseEntity.ok().body(libro);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Libro> updateLibro(@RequestBody Libro libro, @PathVariable("id") Long id) {
        Libro updatedLibro = libroService.update(id, libro);
        return ResponseEntity.ok().body(updatedLibro);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteLibroById(@PathVariable("id") Long id) {
        boolean deleted = libroService.delete(id);
        if (deleted) {
            return ResponseEntity.ok().body("Libro con id " + id + " ha sido eliminado exitosamente.");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Libro con id " + id + " no fue encontrado.");
        }
    }
}
