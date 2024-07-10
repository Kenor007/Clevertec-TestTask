package main.java.ru.clevertec.check.service.impl;

import main.java.ru.clevertec.check.model.DiscountCard;
import main.java.ru.clevertec.check.model.Product;
import main.java.ru.clevertec.check.model.Purchase;
import main.java.ru.clevertec.check.service.PurchaseService;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class PurchaseServiceImpl implements PurchaseService {
    private static PurchaseServiceImpl purchaseServiceInstance;
    private static final int WHOLESALE_QUANTITY = 5;
    private static final int WHOLESALE_DISCOUNT = 10;
    private static final int DEFAULT_DISCOUNT = 2;
    private static final BigDecimal WHOLESALE_DISCOUNT_RATE = BigDecimal.valueOf(WHOLESALE_DISCOUNT).divide(BigDecimal.valueOf(100));
    private static final BigDecimal DEFAULT_DISCOUNT_RATE = BigDecimal.valueOf(DEFAULT_DISCOUNT).divide(BigDecimal.valueOf(100));

    private PurchaseServiceImpl() {
    }

    public static PurchaseServiceImpl getPurchaseServiceInstance() {
        if (Objects.isNull(purchaseServiceInstance)) {
            purchaseServiceInstance = new PurchaseServiceImpl();
        }
        return purchaseServiceInstance;
    }

    @Override
    public List<Purchase> createPurchases(Map<Product, Integer> productsWithQuantity, DiscountCard discountCard) {
        List<Purchase> purchases = new ArrayList<>();
        for (Map.Entry<Product, Integer> entry : productsWithQuantity.entrySet()) {
            Product product = entry.getKey();
            Integer quantity = entry.getValue();
            BigDecimal discount = calculateDiscount(product, quantity, discountCard);
            Purchase purchase = new Purchase.PurchaseBuilder()
                    .product(product)
                    .quantity(quantity)
                    .discount(discount)
                    .build();
            purchases.add(purchase);
        }
        return purchases;
    }

    private BigDecimal calculateDiscount(Product product, Integer quantity, DiscountCard discountCard) {
        BigDecimal quantityBD = BigDecimal.valueOf(quantity);
        if (product.isWholesaleProduct() && quantity >= WHOLESALE_QUANTITY) {
            return product.getPrice().multiply(quantityBD)
                    .multiply(WHOLESALE_DISCOUNT_RATE);
        } else if (discountCard != null) {
            return product.getPrice().multiply(quantityBD)
                    .multiply(BigDecimal.valueOf(discountCard.getDiscountAmount()))
                    .divide(BigDecimal.valueOf(100));
        } else {
            return product.getPrice().multiply(quantityBD)
                    .multiply(DEFAULT_DISCOUNT_RATE);
        }
    }
}