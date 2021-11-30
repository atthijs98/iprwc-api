package com.hsleiden.iprwc.api.repo;

import com.hsleiden.iprwc.api.model.ProductImage;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductImageRepo extends JpaRepository<ProductImage, Long> {
}
