package com.hsleiden.iprwc.api.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import java.util.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String englishTitle;

    @Nullable
    private String originalTitle;

    @Nullable
    private String romanizedOriginalTitle;
    private String year;
    private String runtime;
    @Column(length = 500)
    private String plot;
    private String poster;
    private String trailer;
    private Double price;

    @Nullable
    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Collection<ProductImage> productImages = new ArrayList<>();

    @Nullable
    @OneToMany(cascade = CascadeType.ALL)
    private Set<ProductDirector> productDirectors = new HashSet<>();

}
