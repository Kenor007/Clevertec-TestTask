package main.java.ru.clevertec.check.controller;

import main.java.ru.clevertec.check.exceptions.BadRequestException;
import main.java.ru.clevertec.check.exceptions.ExceptionHandler;
import main.java.ru.clevertec.check.exceptions.NotEnoughMoneyException;
import main.java.ru.clevertec.check.model.Receipt;
import main.java.ru.clevertec.check.service.DiscountCardService;
import main.java.ru.clevertec.check.service.ProductService;
import main.java.ru.clevertec.check.service.ReceiptService;
import main.java.ru.clevertec.check.service.impl.DiscountCardServiceImpl;
import main.java.ru.clevertec.check.service.impl.ProductServiceImpl;
import main.java.ru.clevertec.check.service.impl.ReceiptServiceImpl;
import main.java.ru.clevertec.check.util.csv_parsers.FileParser;
import main.java.ru.clevertec.check.util.csv_parsers.readers.DiscountCardFromCSV;
import main.java.ru.clevertec.check.util.csv_parsers.readers.ProductFromCSV;
import main.java.ru.clevertec.check.util.csv_parsers.writers.ReceiptToCSV;

import java.util.Objects;

import static main.java.ru.clevertec.check.exceptions.ExceptionAnswer.*;

public class MainController {
    private static MainController mainControllerInstance;
    private final ProductService productService;
    private final DiscountCardService discountCardService;
    private final ReceiptService receiptService;

    private MainController() {
        this.productService = ProductServiceImpl.getProductServiceInstance();
        this.discountCardService = DiscountCardServiceImpl.getDiscountCardServiceInstance();
        this.receiptService = ReceiptServiceImpl.getReceiptServiceInstance();
    }

    public static MainController getMainControllerInstance() {
        return Objects.requireNonNullElse(mainControllerInstance, new MainController());
    }

    public void run(String[] args) {
        try {
            productService.addAllProducts(FileParser.productsFromString(new ProductFromCSV().readFromCSV()));
            discountCardService.addAllDiscountCards(FileParser.discountCardsFromString(new DiscountCardFromCSV().readFromCSV()));
            Receipt receipt = receiptService.createReceipt(args);
            new ReceiptToCSV().writeToCSV(FileParser.receiptToString(receipt));
            receiptService.printReceipt(receipt);
        } catch (BadRequestException | NotEnoughMoneyException ex) {
            ExceptionHandler.writeException(ex.getMessage());
        } catch (RuntimeException ex) {
            ExceptionHandler.writeException(INTERNAL_SERVER_ERROR);
        }
    }
}