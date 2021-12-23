package ru.gb.gbrest.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import ru.gb.gbrest.dto.OrdersDto;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

@Component
public class OrdersDtoApiImpl {
    private static final String URL = "http://localhost:8456/orders";

    private RestTemplate restTemplate;

    @Autowired
    public OrdersDtoApiImpl(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }

    public List<OrdersDto> getOrders() {
        List<OrdersDto> listOrdersDto = new ArrayList<>();
        Class<List<OrdersDto>> listOrdersDtoClass = (Class<List<OrdersDto>>) listOrdersDto.getClass();
        return restTemplate.getForObject(URI.create(URL), listOrdersDtoClass);
    }
}
