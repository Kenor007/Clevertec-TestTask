package main.java.ru.clevertec.check.model;

import java.math.BigDecimal;

public class Product {
    private final Long id;
    private final String description;
    private final BigDecimal price;
    private final int quantityInStock;
    private final boolean isWholesaleProduct;

    public Product(Long id, String description, double price, int quantityInStock, boolean isWholesaleProduct) {
        this.id = id;
        this.description = description;
        this.price = BigDecimal.valueOf(price).setScale(2, BigDecimal.ROUND_HALF_UP);
        this.quantityInStock = quantityInStock;
        this.isWholesaleProduct = isWholesaleProduct;
    }

    public Long getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public boolean isWholesaleProduct() {
        return isWholesaleProduct;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", description='" + description + '\'' +
                ", price=" + price + "$" +
                ", quantity in stock=" + quantityInStock +
                ", wholesale product=" + isWholesaleProduct +
                '}';
    }
}