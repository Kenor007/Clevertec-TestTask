package main.java.ru.clevertec.check.exceptions;

public class ProductNotFoundException extends BadRequestException {
    public ProductNotFoundException(String message) {
        super(message);
    }
}