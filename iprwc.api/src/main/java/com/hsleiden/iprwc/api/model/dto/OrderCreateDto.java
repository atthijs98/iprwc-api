package com.hsleiden.iprwc.api.model.dto;

import lombok.Data;

import java.util.List;

@Data
public class OrderCreateDto {
    private String orderNumber;
    private String address;
    private String zipcode;
    private String city;
    private String paymentMethod;
    private Double totalPrice;
    List<ItemDto> items;
    private Long userId;
}
