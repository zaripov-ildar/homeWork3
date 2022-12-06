package ru.starstreet.spring.homeWork3.repository;

import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import ru.starstreet.spring.homeWork3.model.Product;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Repository
@Component

public class ProductRepository {
    private List<Product> products;
    private long lastId;

    @PostConstruct
    private void init() {
        lastId = 0;
        products = new ArrayList<>();
        Random rnd = new Random();
        for (int i = 0; i < 5; i++) {
            addProduct("Product #" + (i + 1), rnd.nextInt(500));
        }
    }

    public void addProduct(String title, int cost) {
        products.add(new Product(lastId++, title, cost));
    }



    public Product getProductById(long id) {
        return products.stream()
                .filter(product -> product.getId() == id)
                .findFirst()
                .orElse(null);
    }

    public List<Product> getProducts() {
        return products;
    }

    public void addProduct(Product product) {
        product.setId(++lastId);
        products.add(product);
    }
}
