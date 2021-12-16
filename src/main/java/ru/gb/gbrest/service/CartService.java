package ru.gb.gbrest.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.gb.gbrest.dao.CartDao;
import ru.gb.gbrest.dto.CartDto;
import ru.gb.gbrest.entity.Cart;
import ru.gb.gbrest.entity.Product;
import ru.gb.gbrest.entity.enums.CartStatus;

import java.util.Iterator;
import java.util.List;
import java.util.Optional;
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
            if (p.getId().equals(product.getId())) {
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

    @Transactional
    public List<Cart> getAllCart() {
        return cartDao.findAll();
    }

    @Transactional
    public Cart findById(Long id) {
        return cartDao.findById(id).orElse(null);
    }

    public void deleteById(Long id) {
        cartDao.deleteById(id);
    }

    public CartDto save(CartDto cartDto) {
        Cart savingCart;

        if (cartDto.getId() != null) {
            Optional<Cart> cartFromDbOptional = cartDao.findById(cartDto.getId());
            savingCart = cartFromDbOptional.orElseGet(Cart::new);
        } else {
            savingCart = new Cart();
        }

        savingCart.setStatus(cartDto.getStatus());
        savingCart.setProducts(cartDto.getProducts());
        savingCart = cartDao.save(savingCart);
        cartDto.setId(savingCart.getId());
        return cartDto;
    }
}