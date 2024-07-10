package main.java.ru.clevertec.check.util.csv_parsers.readers;

import main.java.ru.clevertec.check.exceptions.BadRequestException;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static main.java.ru.clevertec.check.exceptions.ExceptionAnswer.FILE_NOT_FOUND;

public abstract class AbstractReaderFromCSV {
    protected String csvFileName;

    public String[] readFromCSV() throws BadRequestException {
        List<String> lines = new ArrayList<>();
        try (Scanner scanner = new Scanner(new File(csvFileName))) {
            while (scanner.hasNextLine()) {
                lines.add(scanner.nextLine());
            }
        } catch (FileNotFoundException e) {
            System.out.println(FILE_NOT_FOUND + e.getMessage());
            throw new BadRequestException(FILE_NOT_FOUND + e.getMessage());
        }
        return lines.toArray(new String[0]);
    }
}