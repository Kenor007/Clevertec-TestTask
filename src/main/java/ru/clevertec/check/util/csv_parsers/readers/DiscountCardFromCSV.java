package main.java.ru.clevertec.check.util.csv_parsers.readers;

public class DiscountCardFromCSV extends AbstractReaderFromCSV {
    private static final String DISCOUNT_CARDS_CSV = "./src/main/resources/discountCards.csv";

    public DiscountCardFromCSV() {
        super.csvFileName = DISCOUNT_CARDS_CSV;
    }
}