package main.java.ru.clevertec.check.service.impl;

import main.java.ru.clevertec.check.exceptions.ProductNotFoundException;
import main.java.ru.clevertec.check.model.Product;
import main.java.ru.clevertec.check.repository.ProductRepository;
import main.java.ru.clevertec.check.repository.impl.ProductRepositoryImpl;
import main.java.ru.clevertec.check.service.ProductService;

import java.util.List;
import java.util.Objects;

import static main.java.ru.clevertec.check.exceptions.ExceptionAnswer.PRODUCT_NOT_FOUND;

public class ProductServiceImpl implements ProductService {
    private static ProductServiceImpl productServiceInstance;
    private final ProductRepository productRepository;

    public ProductServiceImpl() {
        this.productRepository = ProductRepositoryImpl.getProductRepositoryInstance();
    }

    public static ProductServiceImpl getProductServiceInstance() {
        if (Objects.isNull(productServiceInstance)) {
            productServiceInstance = new ProductServiceImpl();
        }
        return productServiceInstance;
    }

    @Override
    public void addAllProducts(List<Product> products) {
        productRepository.addAllProducts(products);
    }

    @Override
    public Product findProductById(Long productId) {
        return productRepository.getAllProducts().stream()
                .filter(p -> p.getId().equals(productId))
                .findFirst()
                .orElseThrow(() -> new ProductNotFoundException(String.format(PRODUCT_NOT_FOUND, productId)));
    }
}