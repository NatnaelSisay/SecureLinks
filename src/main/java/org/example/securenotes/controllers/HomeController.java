package org.example.securenotes.controllers;


import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.securenotes.dto.NoteRequestDTO;
import org.example.securenotes.dto.NoteResponseDTO;
import org.example.securenotes.model.Authorizations;
import org.example.securenotes.model.Note;
import org.example.securenotes.model.NoteUser;
import org.example.securenotes.repository.AuthorizationsRepository;
import org.example.securenotes.services.NoteService;
import org.example.securenotes.utilities.UserUtils;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;


@Controller
@RequiredArgsConstructor
public class HomeController {
    private final NoteService noteService;
    final AuthorizationsRepository authorizationsRepository;

    @GetMapping
    public String home(
            Authentication authentication,
            Model model
    ) {
        // get authenticated user information and send it to the user interface
        if(authentication == null) return "index";

        NoteUser noteUser = UserUtils.getAuthenticatedUserDetail(authentication);
        model.addAttribute("user", noteUser);

        // fetch notes of the user and display on the page
        List<NoteResponseDTO> notes = this.noteService.findAll();
        model.addAttribute("notes", notes);

        return "index";
    }

    @GetMapping("/admin/notes")
    public String adminNotes(
            Authentication authentication,
            Model model
    ){

        NoteUser noteUser = UserUtils.getAuthenticatedUserDetail(authentication);
        model.addAttribute("user", noteUser);

        Authorizations authorizations = this.authorizationsRepository.findByEmail(noteUser.email()).orElse(null);
        if(authorizations != null && authorizations.getRole() != null && authorizations.getRole().getName().equals("ROLE_ADMIN")) {
            List<Note> notes = this.noteService.findAllForAdmin();
            model.addAttribute("notes", notes);
            return "admin";
        }

        return "redirect:/";
    }

    @GetMapping("/notes")
    public String addNotes(Model model){
        NoteRequestDTO noteRequest = new NoteRequestDTO();
        model.addAttribute("note", noteRequest);
        return "addNotes";
    }

    @GetMapping("/notes/{noteId}")
    public String viewNote(Model model){
        NoteRequestDTO noteRequest = new NoteRequestDTO();
        model.addAttribute("note", noteRequest);
        return "addNotes";
    }

    @PostMapping("/notes")
    public String saveNotes(
            @Valid @ModelAttribute NoteRequestDTO noteRequest,
            BindingResult bindingResult,
            RedirectAttributes redirectAttributes
    ){
        if(bindingResult.hasErrors()){
            return "addNotes";
        }

        this.noteService.save(noteRequest);
        redirectAttributes.addFlashAttribute("message", "Note saved successfully.");
        return "redirect:/";
    }

    @GetMapping("/notes/update/{noteId}")
    public String updateNote(
            @PathVariable("noteId")
            Long noteId,
            Model model
    ){
        NoteResponseDTO updateNote = this.noteService.findById(noteId);
        model.addAttribute("note", updateNote);
        return "updateNote";
    }

    @PostMapping("/notes/update/{noteId}")
    public String updateNotes(
            @PathVariable("noteId") long id,
            @Valid @ModelAttribute NoteRequestDTO noteRequest,
            BindingResult bindingResult
    ){
        if(bindingResult.hasErrors()){
            return "addNotes";
        }

        this.noteService.update(id, noteRequest);
        return "redirect:/";
    }

    @DeleteMapping("/notes/{noteId}")
    public String deleteNotes(
            @PathVariable("noteId") long id
    ){
        this.noteService.delete(id);
        return "redirect:/";
    }

}
