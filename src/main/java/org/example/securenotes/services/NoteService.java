package org.example.securenotes.services;

import org.example.securenotes.dto.NoteRequestDTO;
import org.example.securenotes.dto.NoteResponseDTO;
import org.example.securenotes.model.Note;

import java.util.List;

public interface NoteService {
//    get note
    public NoteResponseDTO findById(long id);

//    get all notes
    public List<NoteResponseDTO> findAll();
    public List<Note> findAllForAdmin();

//    save note
    public NoteResponseDTO save(NoteRequestDTO noteRequestDTO);

//    update note
    public NoteResponseDTO update(long id, NoteRequestDTO noteRequestDTO);

//    delete note
    public void delete(long id);
}
