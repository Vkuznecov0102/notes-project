package ru.t1.services;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.t1.domains.Note;

import java.text.SimpleDateFormat;
import java.util.*;

@RequiredArgsConstructor
@Service
public class NoteCreationServiceImpl implements NoteCreationService {

    private Note note;

    @Autowired
    private final NoteService noteService;

    @Autowired
    private final MenuReaderService menuReaderService;

    @SneakyThrows
    @Override
    public void noteCreation(int number) {
        boolean isExit = true;
        menuReaderService.menuReader("src/main/resources/menu.txt");
        Scanner sc = new Scanner(System.in);
        System.out.println("Введите цифру");
        number = sc.nextInt();

        while (isExit) {
            if (number == 1) {
                System.out.println("Введите текст заметки");
                Scanner scanner = new Scanner(System.in);
                String text = scanner.nextLine();
                StringBuilder text2 = new StringBuilder(text);

                System.out.println("Введите дату в формате Год-день-месяц");
                String date = scanner.nextLine();
                Date onlyDate = new SimpleDateFormat("yyyy-MM-dd").parse(date);

                System.out.println("Введите хэштеги через enter не более четырех");

                String first = scanner.nextLine();
                String second = scanner.nextLine();
                String third = scanner.nextLine();
                String fourth = scanner.nextLine();
                List<String> hashtagList = new ArrayList<>(List.of(first, second, third, fourth));

                System.out.println("Введите заголовок");
                String header = scanner.nextLine();
                noteService.addNote(new Note(0L, text2, onlyDate, hashtagList, header));
                System.out.println("Спасибо,заметка добавлена");
                System.out.println("Хотите сохранить заметку в файл?");
                if (scanner.nextLine().strip().equalsIgnoreCase("ДА"))
                    noteCreation(5);
                else {
                    isExit = false;
                }

            } else if (number == 2) {
                System.out.println("Введите номер заметки");
                int someNumber = sc.nextInt();
                noteService.deleteNoteById(someNumber);
                System.out.println("Заметка успешно удалена");
                System.out.println("Если хотите закончить напишите ДА");
                if (sc.nextLine().strip().equalsIgnoreCase("ДА")) {
                    isExit = false;
                }
            } else if (number == 3) {
                System.out.println("Введите номер заметки");
                long thirdNumber = sc.nextLong();
                noteService.getNoteById(thirdNumber);
                System.out.println("Введите новый текст для заметки");
                String text = sc.nextLine();
                StringBuilder text2 = new StringBuilder(text);
                System.out.println("Введите дату изменений в формате Год-день-месяц");
                String date = sc.nextLine();
                Date onlyDate = new SimpleDateFormat("yyyy-MM-dd").parse(date);
                noteService.updateTextInNote(new Note(thirdNumber, text2, onlyDate));
                System.out.println("Заметка успешно сохранена");
                System.out.println("Если хотите закончить напишите ДА");
                if (sc.nextLine().strip().equalsIgnoreCase("ДА")) {
                    isExit = false;
                } else {
                    noteCreation(1);
                }
            } else if (number == 4) {
                System.out.println("Введите хэштег");
                String hashtag = sc.nextLine();
                var result = noteService.searchContent(hashtag);
                System.out.println(result);
                System.out.println("На этом всё");
                System.out.println("Если хотите закончить напишите ДА");
                if (sc.nextLine().strip().equalsIgnoreCase("ДА")) {
                    isExit = false;
                } else {
                    noteCreation(1);
                }
            } else if (number == 5) {
                System.out.println("Сохранение заметки в файл");
                System.out.println("Чтобы сохранить заметку введите имя файла");
                String fileName = sc.nextLine();

                System.out.println("Введите текст заметки");
                Scanner scanner = new Scanner(System.in);
                String text = scanner.nextLine();
                StringBuilder text2 = new StringBuilder(text);

                System.out.println("Введите дату в формате Год-день-месяц");
                String date = scanner.nextLine();
                Date onlyDate = new SimpleDateFormat("yyyy-MM-dd").parse(date);

                System.out.println("Введите хэштеги через enter не более четырех");

                String first = scanner.nextLine();
                String second = scanner.nextLine();
                String third = scanner.nextLine();
                String fourth = scanner.nextLine();
                List<String> hashtagList = new ArrayList<>(List.of(first, second, third, fourth));

                System.out.println("Введите заголовок");
                String header = scanner.nextLine();

                noteService.saveToFile(new Note(0L, text2, onlyDate, hashtagList, header), fileName);
                System.out.println("Если хотите закончить напишите ДА");
                if (sc.nextLine().strip().equalsIgnoreCase("ДА")) {
                    isExit = false;
                } else {
                    noteCreation(1);
                }
            }

        }
    }
}
