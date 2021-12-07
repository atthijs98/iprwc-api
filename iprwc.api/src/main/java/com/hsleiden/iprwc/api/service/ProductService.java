package com.hsleiden.iprwc.api.service;

import com.hsleiden.iprwc.api.model.Product;
import com.hsleiden.iprwc.api.model.ProductDirector;
import com.hsleiden.iprwc.api.model.ProductImage;
import com.hsleiden.iprwc.api.model.dto.ProductCreateDto;
import com.hsleiden.iprwc.api.model.dto.ProductImageCreateDto;

import java.util.List;

public interface ProductService {
    Product saveProduct(Product product);
    Product saveProduct(ProductCreateDto product);
    Product updateProduct(Product updatedProduct);
    ProductImage saveProductImage(ProductImage productImage);
    ProductDirector saveProductDirector(ProductDirector productDirector);
    void addImageToProduct(Long productId, Long imageId);
    void addDirectorToProduct(Long productId, Long directorId);
    String deleteIfExists(Integer id);
    List<Product> findAll();
}
