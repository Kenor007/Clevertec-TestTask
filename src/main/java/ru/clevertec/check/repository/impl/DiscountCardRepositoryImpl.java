package main.java.ru.clevertec.check.repository.impl;

import main.java.ru.clevertec.check.model.DiscountCard;
import main.java.ru.clevertec.check.repository.DiscountCardRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class DiscountCardRepositoryImpl implements DiscountCardRepository {
    private static DiscountCardRepositoryImpl discountCardRepositoryInstance;
    private List<DiscountCard> discountCards;

    private DiscountCardRepositoryImpl() {
        this.discountCards = new ArrayList<>();
    }

    public static DiscountCardRepositoryImpl getDiscountCardRepositoryInstance() {
        return Objects.requireNonNullElse(discountCardRepositoryInstance, new DiscountCardRepositoryImpl());
    }

    @Override
    public void addAllDiscountCards(List<DiscountCard> discountCards) {
        this.discountCards = discountCards;
    }

    @Override
    public List<DiscountCard> getAllDiscountCards() {
        return new ArrayList<>(discountCards);
    }
}