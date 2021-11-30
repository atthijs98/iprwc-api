package com.hsleiden.iprwc.api.model.dto;

import lombok.Data;
import org.springframework.lang.Nullable;

import java.util.List;

@Data
public class ProductCreateDto {
    private String englishTitle;
    @Nullable
    private String originalTitle;
    @Nullable
    private String romanizedOriginalTitle;
    private String year;
    private String runtime;
    private String plot;
    private String poster;
    private String trailer;
    private Double price;
    @Nullable
    List<ProductImageCreateDto> productImages;
    @Nullable
    List<ProductDirectorCreateDto> productDirectors;
}
