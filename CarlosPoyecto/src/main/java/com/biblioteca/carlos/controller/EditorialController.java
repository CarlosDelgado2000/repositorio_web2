package com.biblioteca.carlos.controller;

import com.biblioteca.carlos.interfacs.services.IEditorialService;
import com.biblioteca.carlos.model.Editorial;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/editoriales")
public class EditorialController {

    @Autowired
    private IEditorialService editorialService;

    @GetMapping
    public ResponseEntity<List<Editorial>> getAllEditoriales() {
        List<Editorial> editorialList = editorialService.list();
        return ResponseEntity.ok().body(editorialList);
    }

    @PostMapping
    public ResponseEntity<Editorial> saveEditorial(@Validated @RequestBody Editorial editorial) {
        Editorial savedEditorial = editorialService.save(editorial);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedEditorial);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Editorial>> getEditorialById(@PathVariable("id") Long id) {
        Optional<Editorial> editorial = editorialService.listId(id);
        if (editorial.isPresent()) {
            return ResponseEntity.ok().body(editorial);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Editorial> updateEditorial(@RequestBody Editorial editorial, @PathVariable("id") Long id) {
        Editorial updatedEditorial = editorialService.update(id, editorial);
        return ResponseEntity.ok().body(updatedEditorial);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteEditorialById(@PathVariable("id") Long id) {
        boolean deleted = editorialService.delete(id);
        if (deleted) {
            return ResponseEntity.ok().body("Editorial con id " + id + " ha sido eliminado exitosamente.");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Editorial con id " + id + " no fue encontrado.");
        }
    }
}
