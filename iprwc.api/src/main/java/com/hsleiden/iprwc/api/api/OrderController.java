package com.hsleiden.iprwc.api.api;

import com.hsleiden.iprwc.api.exception.ResourceNotFoundException;
import com.hsleiden.iprwc.api.model.Item;
import com.hsleiden.iprwc.api.model.Order;
import com.hsleiden.iprwc.api.model.dto.ItemDto;
import com.hsleiden.iprwc.api.model.dto.OrderCreateDto;
import com.hsleiden.iprwc.api.service.ItemService;
import com.hsleiden.iprwc.api.service.OrderService;
import com.hsleiden.iprwc.api.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;
    private final ProductService productService;
    private final ItemService itemService;

    @PostMapping("/order")
    public ResponseEntity<Order> createOrder(@RequestBody @Valid OrderCreateDto orderCreateDto) {
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/order").toUriString());
        List<ItemDto> itemDtos = orderCreateDto.getItemDto();
        validateProductsExistence(itemDtos);
        Order order = orderService.createOrder(orderCreateDto);

        List<Item> items = new ArrayList<>();
        for (ItemDto dto : itemDtos) {
            items.add(itemService.create(new Item(order, productService.find(dto.getProduct().getId()), dto.getAmount(), dto.getTotal())));
        }

        order.setItems(items);

        this.orderService.updateOrder(order);

        return ResponseEntity.created(uri).body(order);
    }

    private void validateProductsExistence(List<ItemDto> itemDtos) {
        List<ItemDto> list = itemDtos
                .stream()
                .filter(obj -> Objects.isNull(productService.find(obj.getProduct().getId())))
                .collect(Collectors.toList());

        if (!CollectionUtils.isEmpty(list)) {
            new ResourceNotFoundException("Product not found");
        }
    }
}
