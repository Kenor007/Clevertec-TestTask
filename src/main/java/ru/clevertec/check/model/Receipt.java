package main.java.ru.clevertec.check.model;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;

public class Receipt {
    private final String date;
    private final String time;
    private final List<Purchase> purchases;
    private final DiscountCard discountCard;
    private final BigDecimal totalPrice;
    private final BigDecimal totalDiscount;
    private final BigDecimal totalPriceWithDiscount;

    private Receipt(ReceiptBuilder builder) {
        this.date = builder.date;
        this.time = builder.time;
        this.purchases = builder.purchases;
        this.discountCard = builder.discountCard;
        this.totalPrice = builder.totalPrice.setScale(2, BigDecimal.ROUND_HALF_UP);
        this.totalDiscount = builder.totalDiscount.setScale(2, BigDecimal.ROUND_HALF_UP);
        this.totalPriceWithDiscount = builder.totalPriceWithDiscount.setScale(2, BigDecimal.ROUND_HALF_UP);
    }

    public static class ReceiptBuilder {
        private String date;
        private String time;
        private List<Purchase> purchases;
        private DiscountCard discountCard;
        private BigDecimal totalPrice;
        private BigDecimal totalDiscount;
        private BigDecimal totalPriceWithDiscount;

        public ReceiptBuilder date(String date) {
            this.date = date;
            return this;
        }

        public ReceiptBuilder time(String time) {
            this.time = time;
            return this;
        }

        public ReceiptBuilder purchases(List<Purchase> purchases) {
            this.purchases = purchases;
            return this;
        }

        public ReceiptBuilder discountCard(DiscountCard discountCard) {
            this.discountCard = discountCard;
            return this;
        }

        public ReceiptBuilder totalPrice(BigDecimal totalPrice) {
            this.totalPrice = totalPrice;
            return this;
        }

        public ReceiptBuilder totalDiscount(BigDecimal totalDiscount) {
            this.totalDiscount = totalDiscount;
            return this;
        }

        public Receipt build() {
            this.totalPriceWithDiscount = totalPrice.subtract(totalDiscount);
            return new Receipt(this);
        }
    }

    public String getDate() {
        return date;
    }

    public String getTime() {
        return time;
    }

    public List<Purchase> getPurchases() {
        return purchases;
    }

    public DiscountCard getDiscountCard() {
        return discountCard;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public BigDecimal getTotalDiscount() {
        return totalDiscount;
    }

    public BigDecimal getTotalPriceWithDiscount() {
        return totalPriceWithDiscount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Receipt receipt = (Receipt) o;
        return Objects.equals(date, receipt.date) && Objects.equals(time, receipt.time) && Objects.equals(purchases, receipt.purchases) && Objects.equals(discountCard, receipt.discountCard) && Objects.equals(totalPrice, receipt.totalPrice) && Objects.equals(totalDiscount, receipt.totalDiscount) && Objects.equals(totalPriceWithDiscount, receipt.totalPriceWithDiscount);
    }

    @Override
    public int hashCode() {
        return Objects.hash(date, time, purchases, discountCard, totalPrice, totalDiscount, totalPriceWithDiscount);
    }

    @Override
    public String toString() {
        return "Receipt{" +
                "date='" + date + '\'' +
                ", time='" + time + '\'' +
                ", purchases=" + purchases +
                ", discount card=" + discountCard +
                ", total price=" + totalPrice +
                ", total discount=" + totalDiscount +
                ", total price with discount=" + totalPriceWithDiscount +
                '}';
    }
}