package main.java.ru.clevertec.check.service.impl;

import main.java.ru.clevertec.check.exceptions.BadRequestException;
import main.java.ru.clevertec.check.exceptions.DiscountCardNotFoundException;
import main.java.ru.clevertec.check.exceptions.NotEnoughMoneyException;
import main.java.ru.clevertec.check.model.DiscountCard;
import main.java.ru.clevertec.check.model.Product;
import main.java.ru.clevertec.check.model.Purchase;
import main.java.ru.clevertec.check.model.Receipt;
import main.java.ru.clevertec.check.service.DiscountCardService;
import main.java.ru.clevertec.check.service.ProductService;
import main.java.ru.clevertec.check.service.PurchaseService;
import main.java.ru.clevertec.check.service.ReceiptService;
import main.java.ru.clevertec.check.util.Validator;
import main.java.ru.clevertec.check.util.argument_parsers.ParserHandler;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

import static main.java.ru.clevertec.check.exceptions.ExceptionAnswer.*;

public class ReceiptServiceImpl implements ReceiptService {
    private static ReceiptServiceImpl receiptServiceInstance;
    private static final String DATE_FORMAT = "dd.MM.yyyy";
    private static final String TIME_FORMAT = "HH:mm:ss";
    private final String keyValueSplitter = "-";
    private final ParserHandler parserHandler;
    private final DiscountCardService discountCardService;
    private final ProductService productService;
    private final PurchaseService purchaseService;
    private Map<Product, Integer> productsWithQuantity;

    private ReceiptServiceImpl() {
        this.productsWithQuantity = new HashMap<>();
        this.parserHandler = ParserHandler.getParserHandlerInstance();
        this.discountCardService = DiscountCardServiceImpl.getDiscountCardServiceInstance();
        this.productService = ProductServiceImpl.getProductServiceInstance();
        this.purchaseService = PurchaseServiceImpl.getPurchaseServiceInstance();
    }

    public static ReceiptServiceImpl getReceiptServiceInstance() {
        return Objects.requireNonNullElse(receiptServiceInstance, new ReceiptServiceImpl());
    }

    @Override
    public Receipt createReceipt(String[] arguments) throws BadRequestException {
        Validator.validateArguments(arguments);
        LocalDateTime receiptDateTime = LocalDateTime.now();
        List<String> products = parserHandler.getProductParser().parse(arguments);
        productsWithQuantity = !products.isEmpty() ? getProductsWithQuantity(products) : Collections.emptyMap();
        DiscountCard discountCard = getDiscountCard(arguments);
        Double balanceDebitCard = getBalanceDebitCard(arguments);
        List<Purchase> purchases = purchaseService.createPurchases(productsWithQuantity, discountCard);
        Receipt receipt = new Receipt.ReceiptBuilder()
                .date(receiptDateTime.format(DateTimeFormatter.ofPattern(DATE_FORMAT)))
                .time(receiptDateTime.format(DateTimeFormatter.ofPattern(TIME_FORMAT)))
                .purchases(purchases)
                .discountCard(discountCard)
                .totalPrice(getTotalPrice(purchases))
                .totalDiscount(getTotalDiscount(purchases))
                .build();
        checkBalanceDebitCard(balanceDebitCard, receipt.getTotalPriceWithDiscount());
        return receipt;
    }

    @Override
    public void printReceipt(Receipt receipt) {
        DecimalFormat df = new DecimalFormat("#,##0.00");
        BigDecimal totalPrice = receipt.getTotalPrice();
        BigDecimal totalDiscount = receipt.getTotalDiscount();
        BigDecimal totalPriceWithDiscount = receipt.getTotalPriceWithDiscount();
        DiscountCard discountCard = receipt.getDiscountCard();
        System.out.println("-------------------------------------------");
        System.out.println("Date\tTime");
        System.out.println(receipt.getDate() + "\t" + receipt.getTime());
        System.out.println();
        System.out.println("QTY\tDESCRIPTION\tPRICE\tDISCOUNT\tTOTAL");
        for (Purchase purchase : receipt.getPurchases()) {
            System.out.println(purchase.getQuantity() + "\t"
                    + purchase.getProduct().getDescription() + "\t"
                    + df.format(purchase.getProduct().getPrice()) + "$\t"
                    + df.format(purchase.getDiscount()) + "$\t"
                    + df.format(purchase.getTotalPrice()) + "$");
        }
        System.out.println();
        if (Objects.nonNull(discountCard)) {
            System.out.println("DISCOUNT CARD\tDISCOUNT PERCENTAGE");
            System.out.println(discountCard.getNumber() + "\t" + discountCard.getDiscountAmount() + "%");
            System.out.println();
        }
        System.out.println("TOTAL PRICE\tTOTAL DISCOUNT\tTOTAL WITH DISCOUNT");
        System.out.println(df.format(totalPrice) + "$\t"
                + df.format(totalDiscount) + "$\t"
                + df.format(totalPriceWithDiscount) + "$");
    }

    private Map<Product, Integer> getProductsWithQuantity(List<String> productsWithQuantity) {
        for (String product : productsWithQuantity) {
            String[] split = product.split(keyValueSplitter);
            long productId = Long.parseLong(split[0]);
            int productQuantity = Integer.parseInt(split[1]);
            Product item = productService.findProductById(productId);
            if (this.productsWithQuantity.containsKey(item)) {
                int oldValue = this.productsWithQuantity.get(item);
                this.productsWithQuantity.replace(item, oldValue, oldValue + productQuantity);
            } else {
                this.productsWithQuantity.put(item, productQuantity);
            }
        }
        return this.productsWithQuantity;
    }

    private DiscountCard getDiscountCard(String[] arguments) {
        DiscountCard discountCard = null;
        List<String> cards = parserHandler.getDiscountCardParser().parse(arguments);
        if (cards.size() == 1) {
            String discountCardNumber = cards.get(0).split("=")[1];
            discountCard = discountCardService.findDiscountCardByNumber(discountCardNumber)
                    .orElseThrow(() -> new DiscountCardNotFoundException(String.format(DISCOUNT_CARD_NOT_FOUND, discountCardNumber)));
        }
        return discountCard;
    }

    private double getBalanceDebitCard(String[] arguments) {
        Double balanceDebitCard = null;
        List<String> debitCardBalances = parserHandler.getDebitCardParser().parse(arguments);
        if (!debitCardBalances.isEmpty()) {
            if (debitCardBalances.size() == 1) {
                balanceDebitCard = Double.parseDouble(debitCardBalances.get(0).split("=")[1]);
            }
        }
        return balanceDebitCard;
    }

    private BigDecimal getTotalPrice(List<Purchase> purchases) {
        BigDecimal total = BigDecimal.ZERO;
        for (Purchase purchase : purchases) {
            total = total.add(purchase.getTotalPrice());
        }
        return total;
    }

    private BigDecimal getTotalDiscount(List<Purchase> purchases) {
        BigDecimal totalDiscount = BigDecimal.ZERO;
        for (Purchase purchase : purchases) {
            totalDiscount = totalDiscount.add(purchase.getDiscount());
        }
        return totalDiscount;
    }

    private void checkBalanceDebitCard(Double balanceDebitCard, BigDecimal totalPriceWithDiscount) {
        if (balanceDebitCard == null || totalPriceWithDiscount == null) {
            throw new BadRequestException(BAD_REQUEST);
        }
        if (BigDecimal.valueOf(balanceDebitCard).compareTo(totalPriceWithDiscount) < 0) {
            throw new NotEnoughMoneyException(NOT_ENOUGH_MONEY);
        }
    }
}