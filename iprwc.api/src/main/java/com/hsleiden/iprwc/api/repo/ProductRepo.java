package com.hsleiden.iprwc.api.repo;

import com.hsleiden.iprwc.api.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProductRepo extends JpaRepository<Product, Long> {
    Optional<Product> findByEnglishTitle(String englishTitle);
}
