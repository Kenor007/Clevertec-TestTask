package main.java.ru.clevertec.check.util.csv_parsers.writers;

public class ExceptionToCSV extends AbstractWriterToCSV {
    private static final String EXCEPTION_CSV = "result.csv";

    public ExceptionToCSV() {
        super.csvFileName = EXCEPTION_CSV;
    }
}