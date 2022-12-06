package ru.starstreet.spring.homeWork3.model;


import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Product {
    private long id;
    private String title;
    private int cost;

    public Product() {
    }

    @Override
    public String toString() {
        return title + ": " + cost;
    }
}
