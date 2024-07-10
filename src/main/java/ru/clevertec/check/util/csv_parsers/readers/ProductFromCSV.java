package main.java.ru.clevertec.check.util.csv_parsers.readers;

public class ProductFromCSV extends AbstractReaderFromCSV {
    private static final String PRODUCTS_CSV = "./src/main/resources/products.csv";

    public ProductFromCSV() {
        super.csvFileName = PRODUCTS_CSV;
    }
}