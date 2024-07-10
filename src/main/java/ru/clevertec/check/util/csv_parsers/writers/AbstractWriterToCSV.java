package main.java.ru.clevertec.check.util.csv_parsers.writers;

import main.java.ru.clevertec.check.exceptions.BadRequestException;

import java.io.FileNotFoundException;
import java.io.PrintWriter;

import static main.java.ru.clevertec.check.exceptions.ExceptionAnswer.FILE_NOT_FOUND;

public abstract class AbstractWriterToCSV {
    protected String csvFileName;

    public void writeToCSV(String[] array) throws BadRequestException {
        try (PrintWriter writer = new PrintWriter(csvFileName)) {
            for (String s : array) {
                writer.println(s);
            }
        } catch (FileNotFoundException e) {
            System.out.println(FILE_NOT_FOUND + e.getMessage());
            throw new BadRequestException(FILE_NOT_FOUND + e.getMessage());
        }
    }
}