package main.java.ru.clevertec.check.util.csv_parsers;

import main.java.ru.clevertec.check.exceptions.BadRequestException;
import main.java.ru.clevertec.check.model.DiscountCard;
import main.java.ru.clevertec.check.model.Product;
import main.java.ru.clevertec.check.model.Purchase;
import main.java.ru.clevertec.check.model.Receipt;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static main.java.ru.clevertec.check.exceptions.ExceptionAnswer.NUMBER_FORMAT_EXCEPTION;

public class FileParser {
    private static final String SEPARATOR = ";";
    private static final DecimalFormat PRICE_FORMAT = new DecimalFormat("#0.00$");
    private static final String EXCEPTION_HEADER = "ERROR";

    public static List<Product> productsFromString(String[] array) {
        return Stream.of(array)
                .skip(1)
                .map(line -> line.split(SEPARATOR))
                .filter(tokens -> tokens.length == 5)
                .map(FileParser::createProductFromTokens)
                .collect(Collectors.toList());
    }

    public static List<DiscountCard> discountCardsFromString(String[] array) {
        return Stream.of(array)
                .skip(1)
                .map(line -> line.split(SEPARATOR))
                .filter(tokens -> tokens.length == 3)
                .map(FileParser::createDiscountCardFromTokens)
                .collect(Collectors.toList());
    }

    public static String[] receiptToString(Receipt receipt) {
        List<String> lines = new ArrayList<>();
        lines.add("Date;Time");
        lines.add(receipt.getDate() + SEPARATOR + receipt.getTime());
        lines.add("\nQTY;DESCRIPTION;PRICE;DISCOUNT;TOTAL");
        for (Purchase purchase : receipt.getPurchases()) {
            Product product = purchase.getProduct();
            lines.add(purchase.getQuantity() + SEPARATOR +
                    product.getDescription() + SEPARATOR +
                    PRICE_FORMAT.format(product.getPrice()) + SEPARATOR +
                    PRICE_FORMAT.format(purchase.getDiscount()) + SEPARATOR +
                    PRICE_FORMAT.format(purchase.getTotalPrice()));
        }
        DiscountCard discountCard = receipt.getDiscountCard();
        if (discountCard != null) {
            lines.add("\nDISCOUNT CARD;DISCOUNT PERCENTAGE");
            lines.add(discountCard.getNumber() + SEPARATOR + discountCard.getDiscountAmount() + "%");
        }
        lines.add("\nTOTAL PRICE;TOTAL DISCOUNT;TOTAL WITH DISCOUNT");
        lines.add(receipt.getTotalPrice() + "$" + SEPARATOR +
                receipt.getTotalDiscount() + "$" + SEPARATOR +
                receipt.getTotalPriceWithDiscount() + "$");
        return lines.toArray(new String[0]);
    }

    public static String[] exceptionToString(String message) {
        List<String> lines = new ArrayList<>();
        lines.add(EXCEPTION_HEADER);
        lines.add(message);
        return lines.toArray(new String[0]);
    }

    private static Product createProductFromTokens(String[] tokens) throws BadRequestException {
        try {
            Long id = Long.parseLong(tokens[0]);
            String description = tokens[1];
            double price = Double.parseDouble(tokens[2].replace(",", "."));
            int quantityInStock = Integer.parseInt(tokens[3]);
            boolean isWholesaleProduct = Boolean.parseBoolean(tokens[4]);
            return new Product(id, description, price, quantityInStock, isWholesaleProduct);
        } catch (NumberFormatException e) {
            System.out.println(NUMBER_FORMAT_EXCEPTION + e.getMessage());
            throw new BadRequestException(NUMBER_FORMAT_EXCEPTION + e.getMessage());
        }
    }

    private static DiscountCard createDiscountCardFromTokens(String[] tokens) throws BadRequestException {
        try {
            Long id = Long.parseLong(tokens[0]);
            String number = tokens[1];
            int discountAmount = Integer.parseInt(tokens[2]);
            return new DiscountCard(id, number, discountAmount);
        } catch (NumberFormatException e) {
            System.out.println(NUMBER_FORMAT_EXCEPTION + e.getMessage());
            throw new BadRequestException(NUMBER_FORMAT_EXCEPTION + e.getMessage());
        }
    }
}