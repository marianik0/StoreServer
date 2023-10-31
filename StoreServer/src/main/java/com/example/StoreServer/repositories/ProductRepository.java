package com.example.StoreServer.repositories;

import com.example.StoreServer.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {

}
