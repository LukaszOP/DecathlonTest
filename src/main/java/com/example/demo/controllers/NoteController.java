package com.example.demo.controllers;

import com.example.demo.model.NoteDTO;
import com.example.demo.services.NoteService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/notes")
public class NoteController {

    private final NoteService noteService;

    @PostMapping("/")
    public ResponseEntity addNote(@Validated @RequestBody NoteDTO note) {
        note.setDate(LocalDateTime.now());
        NoteDTO addedNote = noteService.saveNote(note);
        log.debug("Successfully added a new note: " + addedNote);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Location", "/" + addedNote.getId().toString());
        return new ResponseEntity(headers, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteById(@PathVariable Long id) {
        if(!noteService.deleteNote(id)) {
            throw new NotFoundException();
        }
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/{id}")
    public ResponseEntity updateNote(@PathVariable Long id, @Validated @RequestBody NoteDTO note) {
        Optional<NoteDTO> updatedNote = noteService.updateNoteById(id, note);
        log.debug("Successfully updated note: " + updatedNote);
        if( noteService.updateNoteById(id, note).isEmpty()){
            throw new NotFoundException();
        }

        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/search/{title}")
    public List<NoteDTO> findNotesByTitle(@PathVariable String title) {
        return noteService.findNoteByTitle(title);
    }

    @GetMapping("/")
    public List<NoteDTO> getAllNotes() {
        List<NoteDTO> notes = noteService.findAllNotes();
        return notes;
    }
}
