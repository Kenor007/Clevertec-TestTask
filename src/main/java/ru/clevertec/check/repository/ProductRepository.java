package main.java.ru.clevertec.check.repository;

import main.java.ru.clevertec.check.model.Product;

import java.util.List;

public interface ProductRepository {
    void addAllProducts(List<Product> products);

    List<Product> getAllProducts();
}