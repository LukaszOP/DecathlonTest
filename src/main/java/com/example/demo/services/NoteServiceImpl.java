package com.example.demo.services;

import com.example.demo.entities.Note;
import com.example.demo.mapper.NoteEntityMapper;
import com.example.demo.model.NoteDTO;
import com.example.demo.repositories.NoteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicReference;

@RequiredArgsConstructor
@Service
public class NoteServiceImpl implements NoteService{

    private final NoteRepository noteRepository;

    @Override
    @Transactional
    public NoteDTO saveNote(NoteDTO note) {
        Note savedNote= noteRepository.save(NoteEntityMapper.INSTANCE.map(note));
        return NoteEntityMapper.INSTANCE.map(savedNote);
    }

    @Override
    public Boolean deleteNote(Long noteId) {
        if (noteRepository.existsById(noteId)) {
            noteRepository.deleteById(noteId);
            return true;
        }
        return false;
    }

    @Override
    @Transactional
    public Optional<NoteDTO> updateNoteById(Long id, NoteDTO updatedNote) {
        AtomicReference<Optional<NoteDTO>> atomicReference = new AtomicReference<>();

        noteRepository.findById(id).ifPresentOrElse(foundNote -> {
            foundNote.setContent(updatedNote.getContent());
            foundNote.setTitle(updatedNote.getTitle());
            foundNote.setDate(updatedNote.getDate());
            atomicReference.set(Optional.of(NoteEntityMapper.INSTANCE.map(noteRepository.save(foundNote))));
        }, () -> {
            atomicReference.set(Optional.empty());
        });

        return atomicReference.get();
    }

    @Override
    public List<NoteDTO> findNoteByTitle(String title) {
        List<Note> notes = noteRepository.findByTitle(title);
        return NoteEntityMapper.INSTANCE.map(notes);
    }

    @Override
    public List<NoteDTO> findAllNotes() {
        List<Note> notes = noteRepository.findAll();
        return NoteEntityMapper.INSTANCE.map(notes);
    }
}
