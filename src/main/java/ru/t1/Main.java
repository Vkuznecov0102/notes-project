package ru.t1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import ru.t1.services.NoteCreationService;

import java.sql.SQLException;

@SpringBootApplication
public class Main {
    public static void main(String[] args) throws SQLException {
        var context = SpringApplication.run(Main.class);
        context.getBean(NoteCreationService.class).noteCreation(1);
    }

    /*К сожалению, не успел реализовать функционал полностью, поскольку есть еще
    идеи как это задание можно выполнить. Благодарен за возможность попрактиковаться:)
     */
}
