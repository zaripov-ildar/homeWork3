package ru.starstreet.spring.homeWork3.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.starstreet.spring.homeWork3.model.Product;
import ru.starstreet.spring.homeWork3.repository.ProductRepository;

@Controller

public class StoreController {
    private ProductRepository repository;

    @Autowired
    public void setRepository(ProductRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/products")
    public String getProductList(Model model, @RequestParam(defaultValue = "-1") String prodId) {
        model.addAttribute("products", repository.getProducts());
        model.addAttribute("product", new Product());
        String product = getProductById(prodId);
        model.addAttribute("foundProduct", product);

        return "HTML/productList";
    }

    private String getProductById(String prodId) {
        long id;
        try {
            id = Long.parseLong(prodId);
        } catch (NumberFormatException e) {
            return prodId + " - is not a number";
        }
        Product product = repository.getProductById(id);
        return product == null ? "" : product.toString();
    }

    @PostMapping("/add")
    public String addProduct(@ModelAttribute Product product) {
        repository.addProduct(product);
        return "redirect:/products";
    }
}
