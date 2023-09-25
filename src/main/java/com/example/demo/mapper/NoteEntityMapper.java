package com.example.demo.mapper;

import com.example.demo.entities.Note;
import com.example.demo.model.NoteDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface NoteEntityMapper {

    NoteEntityMapper INSTANCE = Mappers.getMapper(NoteEntityMapper.class);

    NoteDTO map(Note note);

    Note map(NoteDTO note);

    List<NoteDTO> map(List<Note> notes);
}