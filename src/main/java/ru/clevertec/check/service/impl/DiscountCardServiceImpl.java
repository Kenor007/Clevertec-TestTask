package main.java.ru.clevertec.check.service.impl;

import main.java.ru.clevertec.check.model.DiscountCard;
import main.java.ru.clevertec.check.repository.DiscountCardRepository;
import main.java.ru.clevertec.check.repository.impl.DiscountCardRepositoryImpl;
import main.java.ru.clevertec.check.service.DiscountCardService;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class DiscountCardServiceImpl implements DiscountCardService {
    private static DiscountCardServiceImpl discountCardServiceInstance;
    private final DiscountCardRepository discountCardRepository;

    public DiscountCardServiceImpl() {
        this.discountCardRepository = DiscountCardRepositoryImpl.getDiscountCardRepositoryInstance();
    }

    public static DiscountCardServiceImpl getDiscountCardServiceInstance() {
        if (Objects.isNull(discountCardServiceInstance)) {
            discountCardServiceInstance = new DiscountCardServiceImpl();
        }
        return discountCardServiceInstance;
    }

    @Override
    public void addAllDiscountCards(List<DiscountCard> discountCards) {
        discountCardRepository.addAllDiscountCards(discountCards);
    }

    @Override
    public Optional<DiscountCard> findDiscountCardByNumber(String discountCardNumber) {
        return discountCardRepository.getAllDiscountCards().stream()
                .filter(dc -> dc.getNumber().equals(discountCardNumber))
                .findFirst();
    }
}