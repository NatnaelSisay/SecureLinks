package org.example.securenotes.dto;

import org.example.securenotes.model.Note;

import java.util.List;

public class NoteAdapter {
    public static Note getNoteFromRequest(NoteRequestDTO noteRequestDTO) {
        return new Note(noteRequestDTO.getTitle(), noteRequestDTO.getContent());
    }

    public static NoteResponseDTO getNoteResponseDTO(Note note) {
        return new NoteResponseDTO(note.getId(), note.getTitle(), note.getContent());
    }

    public static List<NoteResponseDTO> getNoteResponseDTOList(List<Note> noteList) {
        return noteList.stream().map(NoteAdapter::getNoteResponseDTO).toList();
    }

}
