package main.java.ru.clevertec.check.model;

import java.math.BigDecimal;
import java.util.Objects;

public class Purchase {
    private final Product product;
    private final int quantity;
    private final BigDecimal discount;
    private final BigDecimal totalPrice;

    private Purchase(PurchaseBuilder builder) {
        this.product = builder.product;
        this.quantity = builder.quantity;
        this.discount = builder.discount.setScale(2, BigDecimal.ROUND_HALF_UP);
        this.totalPrice = builder.totalPrice.setScale(2, BigDecimal.ROUND_HALF_UP);
    }

    public static class PurchaseBuilder {
        private Product product;
        private int quantity;
        private BigDecimal discount;
        private BigDecimal totalPrice;

        public PurchaseBuilder product(Product product) {
            this.product = product;
            return this;
        }

        public PurchaseBuilder quantity(int quantity) {
            this.quantity = quantity;
            return this;
        }

        public PurchaseBuilder discount(BigDecimal discount) {
            this.discount = discount;
            return this;
        }

        public Purchase build() {
            this.totalPrice = BigDecimal.valueOf(quantity).multiply(product.getPrice());
            return new Purchase(this);
        }
    }

    public Product getProduct() {
        return product;
    }

    public int getQuantity() {
        return quantity;
    }

    public BigDecimal getDiscount() {
        return discount;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Purchase purchase = (Purchase) o;
        return quantity == purchase.quantity && Objects.equals(product, purchase.product) && Objects.equals(discount, purchase.discount) && Objects.equals(totalPrice, purchase.totalPrice);
    }

    @Override
    public int hashCode() {
        return Objects.hash(product, quantity, discount, totalPrice);
    }

    @Override
    public String toString() {
        return "Purchase{" +
                "product=" + product +
                ", quantity=" + quantity +
                ", discount=" + discount +
                ", total price=" + totalPrice +
                '}';
    }
}