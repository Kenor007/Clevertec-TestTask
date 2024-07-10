package main.java.ru.clevertec.check.exceptions;

public class BadRequestException extends RuntimeException {
    public BadRequestException() {
    }

    public BadRequestException(String message) {
        super(message);
    }
}