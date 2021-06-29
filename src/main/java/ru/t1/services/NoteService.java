package ru.t1.services;

import ru.t1.domains.Note;

import java.io.IOException;
import java.util.List;

public interface NoteService {
    Note getNoteById(long id);

    void addNote(Note note);

    void updateTextInNote(Note note);

    void deleteNoteById(long id);

    List<Note> searchContent(String text);

    void saveToFile(Note note, String fileName) throws IOException;
}
