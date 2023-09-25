package com.example.demo.controllers;

import com.example.demo.entities.Note;
import com.example.demo.mapper.NoteEntityMapper;
import com.example.demo.model.NoteDTO;
import com.example.demo.repositories.NoteRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
public class NoteControllerIT {

    @Autowired
    NoteController noteController;

    @Autowired
    NoteRepository noteRepository;

    @Rollback
    @Transactional
    @Test
    void saveNewNote() {
        NoteDTO noteDTO = NoteDTO.builder()
                .title("New note")
                .content("New note content")
                .build();

        ResponseEntity responseEntity = noteController.addNote(noteDTO);

        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatusCode.valueOf(201));
        assertThat(responseEntity.getHeaders().getLocation()).isNotNull();

        String[] locationID = responseEntity.getHeaders().getLocation().getPath().split("/");
        Long savedID =  Long.parseLong(locationID[1]);

        Note note = noteRepository.findById(savedID).get();
        assertThat(note).isNotNull();
    }

    @Rollback
    @Transactional
    @Test
    void deleteByIdFound() {
        Note note = noteRepository.findAll().get(0);

        ResponseEntity responseEntity = noteController.deleteById(note.getId());
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatusCode.valueOf(204));

        assertThat(noteRepository.findById(note.getId()).isEmpty());
    }

    @Test
    void testDeleteByIDNotFound() {
        assertThrows(NotFoundException.class, () -> {
            noteController.deleteById(17L);
        });
    }

    @Test
    void testUpdateNotFound() {
        assertThrows(NotFoundException.class, () -> {
            noteController.updateNote(17L, NoteDTO.builder().build());
        });
    }

    @Rollback
    @Transactional
    @Test
    void updateExistingNote() {
        Note note = noteRepository.findAll().get(0);
        NoteDTO noteDTO = NoteEntityMapper.INSTANCE.map(note);
        noteDTO.setId(null);
        final String noteName = "UPDATED";
        noteDTO.setTitle(noteName);

        ResponseEntity responseEntity = noteController.updateNote(note.getId(), noteDTO);
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatusCode.valueOf(204));

        Note updatedNote = noteRepository.findById(note.getId()).get();
        assertThat(updatedNote.getTitle()).isEqualTo(noteName);
    }

    @Test
    void testNoteIdNotFound() {
        assertThat(noteController.findNotesByTitle("Title that doesn't exist").isEmpty());
    }

    @Test
    void testGetByTitle() {
        Note note = noteRepository.findAll().get(0);
        List<NoteDTO> foundNotes = noteController.findNotesByTitle("Notatka z wczoraj");
        assertThat(foundNotes.size()).isEqualTo(3);
    }

    @Test
    void testListNotes() {
        List<NoteDTO> allNotes = noteController.getAllNotes();

        assertThat(allNotes.size()).isEqualTo(3);
    }
}