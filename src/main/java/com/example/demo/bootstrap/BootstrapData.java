package com.example.demo.bootstrap;

import com.example.demo.entities.Note;
import com.example.demo.repositories.NoteRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Slf4j
@Component
public class BootstrapData implements CommandLineRunner {

    private final NoteRepository noteRepository;

    public BootstrapData(NoteRepository noteRepository) {
        this.noteRepository = noteRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        Note note1 = new Note();
        note1.setContent("Notatka ze spotkania");
        note1.setTitle("Notatka z wczoraj");
        note1.setDate(LocalDateTime.now());
        Note note2 = new Note();
        note2.setContent("Notatka ze spotkania");
        note2.setTitle("Notatka z wczoraj");
        note2.setDate(LocalDateTime.now());
        Note note3 = new Note();
        note3.setContent("Notatka ze spotkania");
        note3.setTitle("Notatka z wczoraj");
        note3.setDate(LocalDateTime.now());
        Note savedNote1 = noteRepository.save(note1);
        Note savedNote2 = noteRepository.save(note2);
        Note savedNote3 = noteRepository.save(note3);

        log.info("Laduje przykladowe dane do bazy danych h2 (in memory).");
        log.info("Notatka 1: " + savedNote1);
        log.info("Notatka 2: " + savedNote2);
        log.info("Notatka 3: " + savedNote3);
    }
}
