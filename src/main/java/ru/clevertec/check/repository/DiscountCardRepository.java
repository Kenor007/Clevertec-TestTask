package main.java.ru.clevertec.check.repository;

import main.java.ru.clevertec.check.model.DiscountCard;

import java.util.List;

public interface DiscountCardRepository {
    void addAllDiscountCards(List<DiscountCard> discountCards);

    List<DiscountCard> getAllDiscountCards();
}