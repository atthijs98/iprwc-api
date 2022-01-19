package com.hsleiden.iprwc.api.model.dto;

import com.hsleiden.iprwc.api.model.Product;
import com.hsleiden.iprwc.api.model.User;
import lombok.Data;

@Data
public class ItemDto {
    private Integer amount;
    private Double total;
    private Product product;
    private User user;

    public Product getProduct() {
        return product;
    }

    public User getUser() {
        return user;
    }
}
