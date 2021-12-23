package ru.gb.gbrest.service.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import ru.gb.gbrest.dto.OrdersDto;

@FeignClient(url = "localhost:8456/orders", value = "OrderDtoApi")
public interface OrderDtoApi {
    @GetMapping
    OrdersDto getOrders();
}
