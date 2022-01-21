package com.hsleiden.iprwc.api.service;

import com.hsleiden.iprwc.api.model.Order;
import com.hsleiden.iprwc.api.model.User;
import com.hsleiden.iprwc.api.model.dto.OrderCreateDto;
import com.hsleiden.iprwc.api.repo.OrderRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class OrderServiceImplementation implements OrderService {
    private final OrderRepo orderRepo;
    private final UserService userService;

    @Override
    public Iterable<Order> getAllOrders() {
        return this.orderRepo.findAll();
    }

    @Override
    public Order createOrder(OrderCreateDto orderCreateDto) {
        Order order = new Order();
        order.setOrderNumber(orderCreateDto.getOrderNumber());
        order.setAddress(orderCreateDto.getAddress());
        order.setZipcode(orderCreateDto.getZipcode());
        order.setCity(orderCreateDto.getCity());
        order.setPaymentMethod(orderCreateDto.getPaymentMethod());
        order.setTotalPrice(orderCreateDto.getTotalPrice());
        order.setDateCreated(LocalDateTime.now());
        order.setUserId(orderCreateDto.getUserId());

        return this.orderRepo.save(order);
    }

    @Override
    public Iterable<Order> getUserOrders() {
        Long id = this.userService.findLoggedInUser().getId();
        if(this.orderRepo.findByUserId(id).isPresent()) {
            return this.orderRepo.findByUserId(id).get();
        } else {
            return null;
        }
    }

    @Override
    public void updateOrder(Order order) {
        this.orderRepo.save(order);
    }
}
