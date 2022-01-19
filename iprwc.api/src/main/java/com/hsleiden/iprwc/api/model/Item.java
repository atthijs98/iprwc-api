package com.hsleiden.iprwc.api.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
public class Item {

    @EmbeddedId
    @JsonIgnore
    private ItemPk pk;

    private Integer amount;

    private Double total;

    public Item() {
        super();
    }

    public Item(Order order, Product product, Integer amount, Double total) {
        pk = new ItemPk();
        pk.setOrder(order);
        pk.setProduct(product);
        this.amount = amount;
        this.total = total;
    }

    public ItemPk getPk() {
        return pk;
    }

    public void setPk(ItemPk pk) {
        this.pk = pk;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

}
