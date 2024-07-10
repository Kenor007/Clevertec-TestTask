package main.java.ru.clevertec.check.model;

import java.util.Objects;

public class DiscountCard {
    private final Long id;
    private final String number;
    private final int discountAmount;

    public DiscountCard(Long id, String number, int discountAmount) {
        this.id = id;
        this.number = number;
        this.discountAmount = discountAmount;
    }

    public Long getId() {
        return id;
    }

    public String getNumber() {
        return number;
    }

    public int getDiscountAmount() {
        return discountAmount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DiscountCard that = (DiscountCard) o;
        return discountAmount == that.discountAmount && Objects.equals(id, that.id) && Objects.equals(number, that.number);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, number, discountAmount);
    }

    @Override
    public String toString() {
        return "DiscountCard{" +
                "id=" + id +
                ", number='" + number + '\'' +
                ", discount amount=" + discountAmount + "%" +
                '}';
    }
}