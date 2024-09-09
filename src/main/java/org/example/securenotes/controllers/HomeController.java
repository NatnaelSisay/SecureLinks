package org.example.securenotes.controllers;


import lombok.RequiredArgsConstructor;
import org.example.securenotes.dto.NoteRequestDTO;
import org.example.securenotes.dto.NoteResponseDTO;
import org.example.securenotes.model.Note;
import org.example.securenotes.model.NoteUser;
import org.example.securenotes.services.NoteService;
import org.example.securenotes.utilities.UserUtils;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Controller
@RequiredArgsConstructor
public class HomeController {
    private final NoteService noteService;

    @GetMapping
    public String home(
            Authentication authentication,
            Model model
    ) {
        // get authenticated user information and send it to the user interface
        if(authentication == null) return "index";

        NoteUser noteUser = UserUtils.getAuthenticatedUserDetail(authentication);
        model.addAttribute("user", noteUser);

//        fetch notes of the user and display on the page
        List<NoteResponseDTO> notes = this.noteService.findAll();
        model.addAttribute("notes", notes);
//        have a link to add a new note
        return "index";
    }

    @GetMapping("/notes")
    public String addNotes(Model model){
        NoteRequestDTO noteRequest = new NoteRequestDTO();
        model.addAttribute("note", noteRequest);
        return "addNotes";
    }

    @PostMapping("/notes")
    public String saveNotes(@ModelAttribute NoteRequestDTO noteRequest){
        this.noteService.save(noteRequest);
        return "redirect:/";
    }

    @PutMapping("/notes/{noteId}")
    public String updateNotes(@PathVariable("noteId") long id, @ModelAttribute NoteRequestDTO noteRequest){
        this.noteService.update(id, noteRequest);
        return "redirect:/";
    }

    @DeleteMapping("/notes/{noteId}")
    public String deleteNotes(@PathVariable("noteId") long id){
        this.noteService.delete(id);
        return "redirect:/";
    }

}
