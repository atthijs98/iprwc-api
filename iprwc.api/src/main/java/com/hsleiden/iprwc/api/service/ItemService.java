package com.hsleiden.iprwc.api.service;

import com.hsleiden.iprwc.api.model.Item;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@Validated
public interface ItemService {
    Item create(@NotNull(message = "The items for order cannot be null.") @Valid Item item);
}
