package com.biblioteca.carlos.controller;

import com.biblioteca.carlos.interfacs.services.IGeneroService;
import com.biblioteca.carlos.model.Genero;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/generos")
public class GeneroController {

    @Autowired
    private IGeneroService generoService;

    @GetMapping
    public ResponseEntity<List<Genero>> getAllGeneros() {
        List<Genero> generoList = generoService.list();
        return ResponseEntity.ok().body(generoList);
    }

    @PostMapping
    public ResponseEntity<Genero> saveGenero(@Validated @RequestBody Genero genero) {
        Genero savedGenero = generoService.save(genero);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedGenero);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Genero>> getGeneroById(@PathVariable("id") Long id) {
        Optional<Genero> genero = generoService.listId(id);
        if (genero.isPresent()) {
            return ResponseEntity.ok().body(genero);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Genero> updateGenero(@RequestBody Genero genero, @PathVariable("id") Long id) {
        Genero updatedGenero = generoService.update(id, genero);
        return ResponseEntity.ok().body(updatedGenero);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteGeneroById(@PathVariable("id") Long id) {
        boolean deleted = generoService.delete(id);
        if (deleted) {
            return ResponseEntity.ok().body("Genero con id " + id + " ha sido eliminado exitosamente.");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Genero con id " + id + " no fue encontrado.");
        }
    }
}
