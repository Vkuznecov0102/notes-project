package ru.t1.domains;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
public class Note {
    private Long id;
    private StringBuilder text;
    private Date creationDate;
    private List<String> hashtags;
    private String header;

    public Note(Long id, StringBuilder text, Date creationDate) {
        this.id = id;
        this.text = text;
        this.creationDate = creationDate;
    }
}
