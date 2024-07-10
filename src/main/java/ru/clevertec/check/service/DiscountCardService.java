package main.java.ru.clevertec.check.service;

import main.java.ru.clevertec.check.model.DiscountCard;

import java.util.List;
import java.util.Optional;

public interface DiscountCardService {
    void addAllDiscountCards(List<DiscountCard> discountCards);

    Optional<DiscountCard> findDiscountCardByNumber(String discountCardNumber);
}