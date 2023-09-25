package com.example.demo.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
import org.antlr.v4.runtime.misc.NotNull;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.Pattern;
import java.time.LocalDateTime;

@Data
@Entity
public class Note {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotBlank(message = "Title is required")
    @Pattern(regexp = "^[A-Za-z]+$", message = "Title should contain only alphabetic characters")
    private String title;

    @NotBlank(message = "Content is required")
    @Pattern(regexp = "^[A-Za-z0-9\\s]+$", message = "Content should contain alphabetic characters, numbers, and whitespace")
    private String content;

    @NotNull
    @PastOrPresent(message = "Date must be in the past or the present")
    private LocalDateTime date;
}
