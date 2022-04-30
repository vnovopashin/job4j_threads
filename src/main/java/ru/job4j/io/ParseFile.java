package ru.job4j.io;

import java.io.*;
import java.util.function.Predicate;

/**
 * Класс позволяет прочитать файл по заданному условию
 *
 * @author Vasiliy Novopashin
 * @version 1.0
 */
public class ParseFile {
    private final File file;

    public ParseFile(File file) {
        this.file = file;
    }

    public String getContent(Predicate<Character> filter) {
        StringBuilder output = new StringBuilder();
        try (BufferedInputStream reader = new BufferedInputStream(new FileInputStream(file))) {
            int data;
            while ((data = reader.read()) != -1) {
                if (filter.test((char) data)) {
                    output.append((char) data);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return output.toString();
    }
}
