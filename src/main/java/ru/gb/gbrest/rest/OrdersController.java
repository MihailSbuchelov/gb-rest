package ru.gb.gbrest.rest;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.gb.gbrest.dto.CartDto;
import ru.gb.gbrest.dto.OrdersDto;
import ru.gb.gbrest.service.OrdersDtoApiImpl;

import java.net.URI;
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

    @PostMapping("/create")
    public OrdersDto handlePost(@Validated @RequestBody OrdersDto ordersDto) {
        return ordersDtoApi.create(ordersDto);
    }

    @PutMapping("/{id}")
    public void put(@Validated @PathVariable("id") Long id, @Validated @RequestBody OrdersDto ordersDto) {
        ordersDtoApi.put(id, ordersDto);
    }

    @DeleteMapping("/{id}")
    public void del(@Validated @PathVariable("id") Long id) {
        ordersDtoApi.del(id);
    }
}
