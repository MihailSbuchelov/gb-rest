package ru.gb.gbrest.dto;

import lombok.*;
import ru.gb.gbrest.entity.Product;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CartDto {
    private Long id;
    private String status;
    private Set<Product> products;
}
