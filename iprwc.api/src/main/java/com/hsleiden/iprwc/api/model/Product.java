package com.hsleiden.iprwc.api.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

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

//    @JsonManagedReference
//    @Nullable
//    @OneToMany(mappedBy = "product", cascade = CascadeType.REFRESH, orphanRemoval = true, fetch = FetchType.EAGER)
//    List<ProductImage> productImages;

    @Nullable
    @OneToMany(fetch = FetchType.EAGER)
    private Collection<ProductImage> productImages = new ArrayList<>();

    @Nullable
    @OneToMany()
    private Set<ProductDirector> productDirectors;

//    @JsonManagedReference
//    @Nullable
//    @OneToMany(mappedBy = "product", cascade = CascadeType.REFRESH, orphanRemoval = true, fetch = FetchType.EAGER)
//    List<ProductDirector> productDirectors;
}
