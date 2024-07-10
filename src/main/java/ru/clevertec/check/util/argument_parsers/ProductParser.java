package main.java.ru.clevertec.check.util.argument_parsers;

import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;

public class ProductParser implements Parsable {
    private static final Pattern PRODUCT_PATTERN = Pattern.compile("\\d+-\\d+");

    @Override
    public List<String> parse(String[] arguments) {
        return Arrays
                .stream(arguments)
                .filter(arg -> PRODUCT_PATTERN.matcher(arg).matches())
                .toList();
    }
}