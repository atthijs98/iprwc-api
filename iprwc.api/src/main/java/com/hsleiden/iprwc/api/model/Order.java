package com.hsleiden.iprwc.api.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.Valid;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String orderNumber;

    private String address;

    private String zipcode;

    private String city;

    private String paymentMethod;

    private Double totalPrice;

    @JsonFormat(pattern = "dd-MM-yyyy HH:mm")
    private LocalDate dateCreated;

    @JsonManagedReference
    @OneToMany(mappedBy = "pk.order")
    @Valid
    private List<Item> items = new ArrayList<>();

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id", referencedColumnName = "id", nullable = false)
    private User user;

}
