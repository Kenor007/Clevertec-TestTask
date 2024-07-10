package main.java.ru.clevertec.check.service;

import main.java.ru.clevertec.check.model.Receipt;

public interface ReceiptService {
    Receipt createReceipt(String[] arguments);

    void printReceipt(Receipt receipt);
}