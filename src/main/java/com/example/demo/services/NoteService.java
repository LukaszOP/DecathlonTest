package com.example.demo.services;

import com.example.demo.model.NoteDTO;

import java.util.List;
import java.util.Optional;


public interface NoteService {
    NoteDTO saveNote(NoteDTO note);

    Boolean deleteNote(Long noteId);

    Optional<NoteDTO> updateNoteById(Long noteId, NoteDTO note);

    List<NoteDTO> findNoteByTitle(String title);

    List<NoteDTO> findAllNotes();

}
