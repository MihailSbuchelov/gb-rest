package ru.gb.gbrest.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import ru.gb.gbrest.entity.Buyer;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrdersDto {
    @JsonProperty(value = "orderId")
    private Long id;
    private Double cost;
    @JsonProperty(value = "buyer")
    private Buyer buyer;
}
