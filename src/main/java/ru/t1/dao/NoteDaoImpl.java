package ru.t1.dao;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;
import ru.t1.domains.Note;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Repository
@RequiredArgsConstructor
public class NoteDaoImpl implements NoteDao {

    private final NamedParameterJdbcOperations namedParameterJdbcOperations;

    @Override
    public Note getNoteById(long id) {
        String getByIdQuery = "select id,text,creationDate,hashtags,header from note where id=:'" + id + "'";
        return namedParameterJdbcOperations.queryForObject(getByIdQuery, Map.of("id", id), Note.class);
    }

    @Override
    public void addNote(Note note) {
        String insertQuery = "insert into note(text,creationDate,hashtags,header) values (?,?,?,?)";
        namedParameterJdbcOperations.getJdbcOperations().update(insertQuery, note.getText(), note.getCreationDate(), note.getHashtags(), note.getHeader());
    }

    @Override
    public void updateTextInNote(Note note) {
        String updateQuery = "update note set text=:text where id=:id";
        SqlParameterSource params = new MapSqlParameterSource().addValue("id", note.getId()).addValue("text", note.getText());
        namedParameterJdbcOperations.update(updateQuery, params);
    }

    @Override
    public void deleteNoteById(long id) {
        String deleteQuery = "delete from note where id = :id";
        SqlParameterSource namedParameters = new MapSqlParameterSource("id", id);
        namedParameterJdbcOperations.update(deleteQuery, namedParameters);
    }

    @Override
    public List<Note> searchContent(String text) {
        String searchQuery = "select id,text,creationDate,hashtags,header from note where text like '" + text + "'";
        return namedParameterJdbcOperations.getJdbcOperations().queryForList(searchQuery, Note.class);
    }

    @Override
    public List<Note> searchByDate(Date creationDate) {
        String searchQuery = "select id,text,creationDate,hashtags,header from note where creationDate =  '" + creationDate + "'";
        return namedParameterJdbcOperations.getJdbcOperations().queryForList(searchQuery, Note.class);
    }

    @Override
    public void saveToFile(Note note, String fileName) {
        Path filePath = Paths.get("src/main/resources/notes/'" + fileName + "'");
        StringBuilder content = note.getText();
        try {
            Files.write(filePath, Collections.singleton(content));
        } catch (IOException exception) {
            exception.getMessage();
        }
    }
}
