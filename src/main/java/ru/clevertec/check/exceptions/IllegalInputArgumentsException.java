package main.java.ru.clevertec.check.exceptions;

public class IllegalInputArgumentsException extends BadRequestException {
    public IllegalInputArgumentsException(String message) {
        super(message);
    }
}