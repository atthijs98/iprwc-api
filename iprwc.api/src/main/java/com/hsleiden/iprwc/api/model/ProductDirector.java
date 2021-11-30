package com.hsleiden.iprwc.api.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductDirector {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

//    @JsonBackReference
//    @JsonIgnoreProperties(value = {"productDirectors"})
//    @ManyToOne(cascade = CascadeType.DETACH)
//    Product product;

    private String name;
}
