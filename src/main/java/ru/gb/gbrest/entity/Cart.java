package ru.gb.gbrest.entity;

import lombok.*;
import ru.gb.gbrest.entity.enums.CartStatus;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "cart")
public class Cart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "status")
    private String status = CartStatus.EMPTY.getTitle();

    @ManyToMany
    @JoinTable(name = "cart_product",
            joinColumns = @JoinColumn(name = "cart_id"),
            inverseJoinColumns = @JoinColumn(name = "product_id"))
    private Set<Product> products = new HashSet<>();

    public boolean addProduct(Product product) {
        if (products == null) {
            products = new HashSet<>();
        }
        return products.add(product);
    }
}