package ru.gb.gbrest.rest;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.gb.gbrest.dto.OrdersDto;
import ru.gb.gbrest.service.OrdersDtoApiImpl;

import java.util.List;

@RestController
@RequestMapping("api/v1/orders")
@RequiredArgsConstructor
public class OrdersController {
    private final OrdersDtoApiImpl ordersDtoApi;

    @GetMapping
    public List<OrdersDto> getOrders() {
        return ordersDtoApi.getOrders();
    }
}
