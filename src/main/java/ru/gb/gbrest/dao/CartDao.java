package ru.gb.gbrest.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.gb.gbrest.entity.Cart;
import ru.gb.gbrest.entity.Product;

import java.util.Set;

public interface CartDao extends JpaRepository<Cart, Long> {
    Set<Product> findAllByProducts(Cart cart);

}