package main.java.ru.clevertec.check.util.argument_parsers;

import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;

public class DiscountCardParser implements Parsable {
    private static final Pattern DISCOUNT_CARD_PATTERN = Pattern.compile("discountCard=\\d+");

    @Override
    public List<String> parse(String[] arguments) {
        return Arrays
                .stream(arguments)
                .filter(arg -> DISCOUNT_CARD_PATTERN.matcher(arg).matches())
                .toList();
    }
}