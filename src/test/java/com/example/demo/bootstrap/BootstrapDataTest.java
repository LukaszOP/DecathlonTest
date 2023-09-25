package com.example.demo.bootstrap;

import com.example.demo.repositories.NoteRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DataJpaTest
public class BootstrapDataTest {

    @Autowired
    private NoteRepository noteRepository;

    private BootstrapData bootstrapData;

    @BeforeEach
    void setUp() {
        bootstrapData = new BootstrapData(noteRepository);
    }

    @Test
    void Testrun() throws Exception {
        bootstrapData.run(null);

        assertThat(noteRepository.count()).isEqualTo(3);
    }
}
