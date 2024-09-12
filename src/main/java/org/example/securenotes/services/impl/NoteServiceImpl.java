package org.example.securenotes.services.impl;

import lombok.RequiredArgsConstructor;
import org.example.securenotes.dto.NoteAdapter;
import org.example.securenotes.dto.NoteRequestDTO;
import org.example.securenotes.dto.NoteResponseDTO;
import org.example.securenotes.model.Note;
import org.example.securenotes.model.NoteUser;
import org.example.securenotes.repository.NoteRepository;
import org.example.securenotes.services.AuthorizationService;
import org.example.securenotes.services.NoteService;
import org.example.securenotes.utilities.UserUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class NoteServiceImpl implements NoteService {

    private final NoteRepository noteRepository;
    private final AuthorizationService authorizationService;

    @Override
    public NoteResponseDTO findById(long id) {
        // handle null case
        Note note = noteRepository.findById(id).orElse(null);

        assert note != null;
        return NoteAdapter.getNoteResponseDTO(note);
    }

    @Override
    public List<NoteResponseDTO> findAll() {
        // handle null cases
        NoteUser user = UserUtils.getAuthenticatedUserDetail(SecurityContextHolder.getContext().getAuthentication());

        List<Note> noteList = new ArrayList<>();
        if(this.authorizationService.isAdmin(user.email())){
            noteList = noteRepository.findAll();
        } else {
            noteList = noteRepository.findNotesByUserEmail(user.email());
        }

        return NoteAdapter.getNoteResponseDTOList(noteList);
    }

    @Override
    public NoteResponseDTO save(NoteRequestDTO noteRequestDTO) {
        NoteUser user = UserUtils.getAuthenticatedUserDetail(SecurityContextHolder.getContext().getAuthentication());

        Note note = NoteAdapter.getNoteFromRequest(noteRequestDTO);
        note.setUserEmail(user.email());
        note = this.noteRepository.save(note);

        return NoteAdapter.getNoteResponseDTO(note);
    }

    @Override
    public NoteResponseDTO update(long id, NoteRequestDTO noteRequestDTO) {
        // handle null
        Note note = noteRepository.findById(id).orElse(null);
        assert note != null;

        note.setTitle(noteRequestDTO.getTitle());
        note.setContent(noteRequestDTO.getContent());
        note = this.noteRepository.save(note);

        return NoteAdapter.getNoteResponseDTO(note);
    }

    @Override
    public void delete(long id) {
        this.noteRepository.deleteById(id);
    }
}
