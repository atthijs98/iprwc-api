package com.hsleiden.iprwc.api.api;

import com.hsleiden.iprwc.api.model.Product;
import com.hsleiden.iprwc.api.model.dto.ProductCreateDto;
import com.hsleiden.iprwc.api.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;

    @GetMapping("/product")
    public ResponseEntity<List<Product>> getProducts(){
        return ResponseEntity.ok().body(productService.findAll());
    }

    @PostMapping("/admin/product")
    public ResponseEntity<Product> createProduct(@RequestBody @Valid ProductCreateDto product) {
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/admin/product").toUriString());
        return ResponseEntity.created(uri).body(productService.saveProduct(product));
    }

    @DeleteMapping("/admin/product/{id}")
    public ResponseEntity<String> deleteById(@PathVariable("id") Integer id) {
        return ResponseEntity
                .ok()
                .body(productService.deleteIfExists(id));
    }
}