package main.java.ru.clevertec.check.exceptions;

public class DiscountCardNotFoundException extends BadRequestException {
    public DiscountCardNotFoundException(String message) {
        super(message);
    }
}