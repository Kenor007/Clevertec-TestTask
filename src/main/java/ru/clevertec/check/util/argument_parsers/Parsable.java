package main.java.ru.clevertec.check.util.argument_parsers;

import java.util.List;

public interface Parsable {
    List<String> parse(String[] arguments);
}