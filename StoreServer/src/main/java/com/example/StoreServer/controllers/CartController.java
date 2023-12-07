package com.example.StoreServer.controllers;


import com.example.StoreServer.dto.AddProductRequest;
import com.example.StoreServer.entities.Product;
import com.example.StoreServer.services.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/carts")
@RequiredArgsConstructor
public class CartController {
    private final CartService cartService;

    @PostMapping("/add")
    public ResponseEntity<HttpStatus> addProduct(@RequestBody AddProductRequest request) {
        cartService.addProduct(request);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @GetMapping("/{userId}")
    public List<Product> find(@PathVariable Long userId) {
        return cartService.find(userId).getProducts();
    }
}
