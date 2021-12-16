package ru.gb.gbrest.rest;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.gb.gbrest.dto.CartDto;
import ru.gb.gbrest.dto.ProductDto;
import ru.gb.gbrest.entity.Cart;
import ru.gb.gbrest.entity.Product;
import ru.gb.gbrest.service.CartService;
import ru.gb.gbrest.service.ProductService;

import java.net.URI;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/cart")
public class CartController {
    private final CartService cartService;

    @GetMapping
    public List<Cart> getCartList() {
        return cartService.getAllCart();
    }

    @GetMapping("/{cartId}")
    public ResponseEntity<?> getCart(@PathVariable("cartId") Long id) {
        Cart cart;
        if (id != null) {
            cart = cartService.findById(id);
            if (cart != null) {
                return new ResponseEntity<>(cart, HttpStatus.OK);
            }
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{cartId}")
    @ResponseStatus
    public void deleteById(@PathVariable("cartId") Long id) {
        cartService.deleteById(id);
    }

    @PutMapping("/{cartId}")
    public ResponseEntity<?> handlePut(@Validated @PathVariable("cartId") Long id, @RequestBody CartDto cartDto) {
        cartDto.setId(id);
        cartService.save(cartDto);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PostMapping
    public ResponseEntity<?> handlePost(@Validated @RequestBody CartDto cartDto) {
        CartDto savedCart = cartService.save(cartDto);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setLocation(URI.create("api/v1/cart/" + savedCart.getId()));
        return new ResponseEntity<>(httpHeaders, HttpStatus.CREATED);
    }
}
