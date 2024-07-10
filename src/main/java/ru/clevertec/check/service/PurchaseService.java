package main.java.ru.clevertec.check.service;

import main.java.ru.clevertec.check.model.DiscountCard;
import main.java.ru.clevertec.check.model.Product;
import main.java.ru.clevertec.check.model.Purchase;

import java.util.List;
import java.util.Map;

public interface PurchaseService {
    List<Purchase> createPurchases(Map<Product, Integer> productsWithQuantity, DiscountCard discountCard);
}