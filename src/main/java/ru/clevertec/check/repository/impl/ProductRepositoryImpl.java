package main.java.ru.clevertec.check.repository.impl;

import main.java.ru.clevertec.check.model.Product;
import main.java.ru.clevertec.check.repository.ProductRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ProductRepositoryImpl implements ProductRepository {
    private static ProductRepositoryImpl productRepositoryInstance;
    private List<Product> products;

    private ProductRepositoryImpl() {
        this.products = new ArrayList<>();
    }

    public static ProductRepositoryImpl getProductRepositoryInstance() {
        return Objects.requireNonNullElse(productRepositoryInstance, new ProductRepositoryImpl());
    }

    @Override
    public void addAllProducts(List<Product> products) {
        this.products = products;
    }

    @Override
    public List<Product> getAllProducts() {
        return new ArrayList<>(products);
    }
}