package org.example.securenotes.repository;

import org.example.securenotes.model.Note;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NoteRepository extends JpaRepository<Note, Long> {
    public List<Note> findNotesByUserEmail(String email);
}
