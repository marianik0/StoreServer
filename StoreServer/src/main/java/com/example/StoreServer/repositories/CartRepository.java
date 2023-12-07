package com.example.StoreServer.repositories;

import com.example.StoreServer.entities.Cart;
import com.example.StoreServer.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

public interface CartRepository extends JpaRepository<Cart, Long> {
    Cart findCartByUser(User user);
}
