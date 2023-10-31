package com.example.StoreServer.controllers;


import com.example.StoreServer.entities.Product;
import com.example.StoreServer.services.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/product")
@RequiredArgsConstructor
public class ProductController {
    private ProductService productService;

    @GetMapping("/{id}")
    public Product findProduct(@PathVariable Long id) {
        return productService.findProduct(id);
    }
    @GetMapping
    public List<Product> findAll() {
        return productService.findAll();
    }

}
