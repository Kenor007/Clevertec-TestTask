package main.java.ru.clevertec.check.util.argument_parsers;

import java.util.Objects;

public class ParserHandler {
    private static ParserHandler parserHandlerInstance;
    private final Parsable productParser;
    private final Parsable discountCardParser;
    private final Parsable debitCardParser;

    private ParserHandler() {
        this.productParser = new ProductParser();
        this.discountCardParser = new DiscountCardParser();
        this.debitCardParser = new DebitCardParser();
    }

    public static ParserHandler getParserHandlerInstance() {
        if (Objects.isNull(parserHandlerInstance)) {
            parserHandlerInstance = new ParserHandler();
        }
        return parserHandlerInstance;
    }

    public Parsable getProductParser() {
        return productParser;
    }

    public Parsable getDiscountCardParser() {
        return discountCardParser;
    }

    public Parsable getDebitCardParser() {
        return debitCardParser;
    }
}