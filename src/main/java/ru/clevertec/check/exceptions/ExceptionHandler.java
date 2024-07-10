package main.java.ru.clevertec.check.exceptions;

import main.java.ru.clevertec.check.util.csv_parsers.FileParser;
import main.java.ru.clevertec.check.util.csv_parsers.writers.ExceptionToCSV;

public class ExceptionHandler {
    public static void writeException(String message) {
        new ExceptionToCSV().writeToCSV(FileParser.exceptionToString(message));
    }
}