package org.example.securenotes.controllers;

import lombok.RequiredArgsConstructor;
import org.example.securenotes.dto.NoteRequestDTO;
import org.example.securenotes.dto.NoteResponseDTO;
import org.example.securenotes.services.NoteService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/notes")
@RequiredArgsConstructor
public class NoteController {
    private final NoteService noteService;

    @GetMapping("/{noteId}")
    public ResponseEntity<NoteResponseDTO> findById(@PathVariable("noteId") long noteId) {
        NoteResponseDTO response = this.noteService.findById(noteId);
        return ResponseEntity.status(200).body(response);
    }

//    findAll
    @GetMapping
    public ResponseEntity<List<NoteResponseDTO>> findAll() {
        List<NoteResponseDTO> response = this.noteService.findAll();
        return ResponseEntity.status(200).body(response);
    }

    @PostMapping
    public ResponseEntity<NoteResponseDTO> create(@RequestBody NoteRequestDTO noteRequestDTO) {
        NoteResponseDTO response = this.noteService.save(noteRequestDTO);
        return ResponseEntity.status(201).body(response);
    }

//    update
    @PutMapping("/{noteId}")
    public ResponseEntity<NoteResponseDTO> update(
            @PathVariable("noteId") long noteId, @RequestBody NoteRequestDTO noteRequestDTO
    ) {
        NoteResponseDTO response = this.noteService.update(noteId, noteRequestDTO);
        return ResponseEntity.status(201).body(response);
    }

//    delete
    @DeleteMapping("/{noteId}")
    public ResponseEntity delete(@PathVariable("noteId") long noteId) {
        this.noteService.delete(noteId);
        return ResponseEntity.status(204).build();
    }
}
