package com.example.StoreServer.services;

import com.example.StoreServer.dto.AddProductRequest;
import com.example.StoreServer.entities.Cart;
import com.example.StoreServer.entities.Product;
import com.example.StoreServer.entities.User;
import com.example.StoreServer.repositories.CartRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CartService {
    private final CartRepository cartRepository;
    private final UserService userService;
    private final ProductService productService;

    @Transactional
    public void addProduct(AddProductRequest request) {
        Cart cart = find(request.userId());
        Product product = productService.findProduct(request.productId());
        List<Product> products = cart.getProducts();
        products.add(product);
        cart.setProducts(products);
        cartRepository.save(cart);
    }

    public Cart find(Long userId) {
        User user = userService.findUser(userId);
        return cartRepository.findCartByUser(user);
    }
}
