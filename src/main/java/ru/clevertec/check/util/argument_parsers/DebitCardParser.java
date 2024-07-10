package main.java.ru.clevertec.check.util.argument_parsers;

import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;

public class DebitCardParser implements Parsable {
    private static final Pattern BALANCE_DEBIT_CARD_PATTERN = Pattern.compile("balanceDebitCard=-?\\d+(\\.\\d+)?");

    @Override
    public List<String> parse(String[] arguments) {
        return Arrays
                .stream(arguments)
                .filter(arg -> BALANCE_DEBIT_CARD_PATTERN.matcher(arg).matches())
                .toList();
    }
}