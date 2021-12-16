package ru.gb.gbrest.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.gb.gbrest.dao.CartDao;
import ru.gb.gbrest.entity.Cart;
import ru.gb.gbrest.entity.Product;
import ru.gb.gbrest.entity.enums.CartStatus;

import java.util.Iterator;
import java.util.Set;

@Service
@RequiredArgsConstructor
@Slf4j
public class CartService {
    @Autowired
    private CartDao cartDao;
    private Cart cart = new Cart();

    @Transactional
    public void addProduct(Product product) {
        cart.addProduct(product);
        if (cart.getStatus().equals(CartStatus.EMPTY.getTitle())) {
            cart.setStatus(CartStatus.NOT_EMPTY.getTitle());
        }
        cartDao.save(cart);
    }

    @Transactional
    public void delProduct(Product product) {
        Set<Product> productSet = cart.getProducts();
        Iterator<Product> itr = productSet.iterator();
        while (itr.hasNext()) {
            Product p = itr.next();
            if (p.getId() == product.getId()) {
                itr.remove();
                return;
            }
        }
        cart.setProducts(productSet);
        if (cart.getProducts().isEmpty()) {
            cart.setStatus(CartStatus.EMPTY.getTitle());
        }
        cartDao.save(cart);
    }

    @Transactional
    public Set<Product> getSetProducts() {
        if (cart.getId() == null) {
            cart.setStatus(CartStatus.EMPTY.getTitle());
            cartDao.save(cart);
        }
        return cart.getProducts();
    }

    public Long getCurCartId() {
        return cart.getId();
    }
}