package main.java.ru.clevertec.check.exceptions;

public final class ExceptionAnswer {
    public static final String FILE_NOT_FOUND = "The file not found";
    public static final String NUMBER_FORMAT_EXCEPTION = "Error when parsing a number";
    public static final String DISCOUNT_CARD_NOT_FOUND = "Discount card with number %s not found";
    public static final String PRODUCT_NOT_FOUND = "The product with id %s not found";
    public static final String INCORRECT_DEBIT_CARD_BALANCE = "Incorrect debit card balance format: %s";
    public static final String INCORRECT_DISCOUNT_CARD_NUMBER = "Incorrect format of the discount card number: %s";
    public static final String ID_QUANTITY_CONSTRAINT = "There must be at least one pair of \"id-quantity\"";
    public static final String DEBIT_CARD_BALANCE_CONSTRAINT = "The debit card balance must be specified";
    public static final String ARGUMENTS_CONSTRAINT = "Arguments cannot be null or empty";
    public static final String BAD_REQUEST = "BAD REQUEST";
    public static final String NOT_ENOUGH_MONEY = "NOT ENOUGH MONEY";
    public static final String INTERNAL_SERVER_ERROR = "INTERNAL SERVER ERROR";
}