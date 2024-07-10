package main.java.ru.clevertec.check.util;

import main.java.ru.clevertec.check.exceptions.ArgumentNotFoundException;
import main.java.ru.clevertec.check.exceptions.BadRequestException;
import main.java.ru.clevertec.check.exceptions.IllegalInputArgumentsException;

import static main.java.ru.clevertec.check.exceptions.ExceptionAnswer.*;

public class Validator {
    private static final String PRODUCT_PATTERN = "\\d+-\\d+";
    private static final String BALANCE_PATTERN = "-?\\d+(\\.\\d+)?";
    private static final String DISCOUNT_CARD_PATTERN = "\\d+";
    private static final String BALANCE_KEY = "balanceDebitCard=";
    private static final String DISCOUNT_KEY = "discountCard=";
    private static final String KEY_VALUE_SPLITTER = "=";

    public static void validateArguments(String[] arguments) {
        if (arguments == null || arguments.length == 0) {
            throw new IllegalInputArgumentsException(ARGUMENTS_CONSTRAINT);
        }

        boolean hasProduct = false;
        boolean hasBalance = false;

        for (String arg : arguments) {
            if (isProductArgument(arg)) {
                hasProduct = true;
            } else if (isBalanceDebitCardArgument(arg)) {
                hasBalance = true;
            } else {
                isDiscountCardArgument(arg);
            }
        }
        if (!hasProduct) {
            throw new ArgumentNotFoundException(ID_QUANTITY_CONSTRAINT);
        }
        if (!hasBalance) {
            throw new ArgumentNotFoundException(DEBIT_CARD_BALANCE_CONSTRAINT);
        }
    }

    private static boolean isProductArgument(String argument) {
        return argument.matches(PRODUCT_PATTERN);
    }

    private static boolean isBalanceDebitCardArgument(String argument) throws BadRequestException {
        if (argument.startsWith(BALANCE_KEY)) {
            String[] splitArguments = argument.split(KEY_VALUE_SPLITTER);
            if (splitArguments.length == 2) {
                String balanceStr = splitArguments[1];
                if (!balanceStr.matches(BALANCE_PATTERN)) {
                    throw new ArgumentNotFoundException(String.format(INCORRECT_DEBIT_CARD_BALANCE, balanceStr));
                }
                return true;
            }
        }
        return false;
    }

    private static boolean isDiscountCardArgument(String argument) throws BadRequestException {
        if (argument.startsWith(DISCOUNT_KEY)) {
            String[] splitArguments = argument.split(KEY_VALUE_SPLITTER);
            if (splitArguments.length == 2) {
                String discountCardStr = splitArguments[1];
                if (!discountCardStr.matches(DISCOUNT_CARD_PATTERN)) {
                    throw new ArgumentNotFoundException(String.format(INCORRECT_DISCOUNT_CARD_NUMBER, discountCardStr));
                }
                return true;
            }
        }
        return false;
    }
}