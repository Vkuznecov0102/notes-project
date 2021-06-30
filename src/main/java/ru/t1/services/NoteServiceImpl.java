package ru.t1.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.t1.dao.NoteDao;
import ru.t1.domains.Note;

import java.io.IOException;
import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class NoteServiceImpl implements NoteService {

    private final NoteDao noteDAO;

    @Override
    public Note getNoteById(long id) {
        return noteDAO.getNoteById(id);
    }

    @Override
    public void addNote(Note note) {
        noteDAO.addNote(note);
    }

    @Override
    public void updateTextInNote(Note note) {
        noteDAO.updateTextInNote(note);
    }

    @Override
    public void deleteNoteById(long id) {
        noteDAO.deleteNoteById(id);
    }

    @Override
    public List<Note> searchContent(String text) {
        return noteDAO.searchContent(text);
    }

    @Override
    public List<Note> searchByDate(Date creationDate) {
        return noteDAO.searchByDate(creationDate);
    }

    @Override
    public void saveToFile(Note note, String fileName) throws IOException {
        noteDAO.saveToFile(note, fileName);
    }
}
