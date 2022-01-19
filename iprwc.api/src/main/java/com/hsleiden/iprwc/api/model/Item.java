package com.hsleiden.iprwc.api.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Item {

    @EmbeddedId
    @JsonIgnore
    private ItemPk pk;

    private Integer amount;

    private Double total;

    public Item(Order order, Product product, Integer amount, Double total) {
        pk = new ItemPk();
        pk.setOrder(order);
        pk.setProduct(product);
        this.amount = amount;
        this.total = total;
    }

}
