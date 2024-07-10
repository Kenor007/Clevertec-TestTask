package main.java.ru.clevertec.check.exceptions;

public class ArgumentNotFoundException extends BadRequestException {
    public ArgumentNotFoundException(String message) {
        super(message);
    }
}