package main.java.ru.clevertec.check.util.csv_parsers.writers;

public class ReceiptToCSV extends AbstractWriterToCSV {
    private static final String RECEIPT_CSV = "result.csv";

    public ReceiptToCSV() {
        super.csvFileName = RECEIPT_CSV;
    }
}