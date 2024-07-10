package main.java.ru.clevertec.check.service;

import main.java.ru.clevertec.check.model.Product;

import java.util.List;

public interface ProductService {
    void addAllProducts(List<Product> products);

    Product findProductById(Long productId);
}