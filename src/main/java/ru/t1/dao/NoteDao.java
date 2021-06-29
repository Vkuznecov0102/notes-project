package ru.t1.dao;

import ru.t1.domains.Note;

import java.io.IOException;
import java.util.List;

public interface NoteDao {

    Note getNoteById(long id);

    void addNote(Note note);

    void updateTextInNote(Note note);

    void deleteNoteById(long id);

    List<Note> searchContent(String text);

    void saveToFile(Note note, String fileName) throws IOException;

//    List<Note> findAll();
}
