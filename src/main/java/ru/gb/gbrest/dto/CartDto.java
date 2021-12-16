package ru.gb.gbrest.dto;

import lombok.*;
import ru.gb.gbrest.entity.Product;

import javax.validation.constraints.NotNull;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CartDto {
    private Long id;
    @NotNull
    private String status;
    @NotNull
    private Set<Product> products;
}
