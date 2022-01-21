package com.hsleiden.iprwc.api.service;

import com.hsleiden.iprwc.api.model.*;
import com.hsleiden.iprwc.api.model.dto.ProductCreateDto;
import com.hsleiden.iprwc.api.model.dto.ProductDirectorCreateDto;
import com.hsleiden.iprwc.api.model.dto.ProductImageCreateDto;
import com.hsleiden.iprwc.api.repo.ProductDirectorRepo;
import com.hsleiden.iprwc.api.repo.ProductImageRepo;
import com.hsleiden.iprwc.api.repo.ProductRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static java.lang.String.format;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class ProductServiceImplementation implements ProductService {
    private final ProductRepo productRepo;
    private final ProductImageRepo productImageRepo;
    private final ProductDirectorRepo productDirectorRepo;

    @Override
    public List<Product> findAll() {
        log.info("Getting all products from database.");
        return productRepo.findAll().parallelStream()
                .collect(Collectors.toList());
    }

    @Override
    public Product saveProduct(Product product) {
        log.info("Saving new product {} to the database", product.getEnglishTitle());
        return productRepo.save(product);
    }

    @Override
    public ProductImage saveProductImage(ProductImage productImage) {
        log.info("Saving new image {} to the database", productImage.getDescription());
        return productImageRepo.save(productImage);
    }

    @Override
    public ProductDirector saveProductDirector(ProductDirector productDirector) {
        log.info("Saving new director {} to the database", productDirector.getName());
        return productDirectorRepo.save(productDirector);
    }

    @Override
    public Product updateProduct(Product updatedProduct) {
        log.info("Saving updated product {} to the database", updatedProduct.getEnglishTitle());
        if (productRepo.findById(updatedProduct.getId()).isPresent()){
            Product existingProduct = productRepo.findById(updatedProduct.getId()).get();
            if (existingProduct != updatedProduct) {
                return null;
            } else {
                return productRepo.save(updatedProduct);
            }
        }else {
            return null;
        }
    }

    @Override
    public void addImageToProduct(Long productId, Long imageId) {
        Product product = productRepo.findById(productId)
                .orElseThrow(
                        () -> new UsernameNotFoundException(
                                format("Product: %s, not found", productId)
                        )
                );
        ProductImage image = productImageRepo.findById(imageId)
                .orElseThrow(
                        () -> new UsernameNotFoundException(
                                format("Image: %s, not found", imageId)
                        )
                );
        assert product.getProductImages() != null;
        product.getProductImages().add(image);
    }

    @Override
    public void addDirectorToProduct(Long productId, Long directorId) {
        Product product = find(Math.toIntExact(productId));
        ProductDirector director = productDirectorRepo.findById(directorId)
                .orElseThrow(
                        () -> new UsernameNotFoundException(
                                format("Image: %s, not found", directorId)
                        )
                );
        assert product.getProductDirectors() != null;
        product.getProductDirectors().add(director);
    }

    @Override
    public Product saveProduct(ProductCreateDto productCreateDto) {
        log.info("Saving new product {} to the database", productCreateDto.getEnglishTitle());
        Product product = new Product();

        product.setEnglishTitle(productCreateDto.getEnglishTitle());
        product.setOriginalTitle(productCreateDto.getOriginalTitle());
        product.setRomanizedOriginalTitle(productCreateDto.getRomanizedOriginalTitle());
        product.setYear(productCreateDto.getYear());
        product.setRuntime(productCreateDto.getRuntime());
        product.setPlot(productCreateDto.getPlot());
        product.setPoster(productCreateDto.getPoster());
        product.setTrailer(productCreateDto.getTrailer());
        product.setPrice(productCreateDto.getPrice());

        Product createdProduct = productRepo.save(product);

        List<ProductImageCreateDto> imageCollection = productCreateDto.getProductImages();
        if (imageCollection != null && !imageCollection.isEmpty()) {
            productCreateDto.getProductImages()
                    .forEach(index -> {
                        ProductImage image = new ProductImage();

                        image.setDescription(index.getDescription());
                        image.setPath(index.getPath());
                        assert createdProduct.getProductImages() != null;
                        createdProduct.getProductImages().add(
                                productImageRepo.save(image)
                        );
                    });

        }

        List<ProductDirectorCreateDto> directorCollection = productCreateDto.getProductDirectors();

        if (directorCollection != null && !directorCollection.isEmpty()) {
            productCreateDto.getProductDirectors()
                    .forEach(index -> {
                        ProductDirector director = new ProductDirector();

                        director.setName(index.getName());

                        assert createdProduct.getProductDirectors() != null;
                        createdProduct.getProductDirectors().add(
                                productDirectorRepo.save(director)
                        );
                    });
        }

        return productRepo.save(createdProduct);
    }

    @Override
    public Product find(Integer id) {
        return productRepo
                .findById(id.longValue())
                .orElseThrow(EntityNotFoundException::new);
    }

    @Override
    public Product find(Long id) {
        return productRepo
                .findById(id)
                .orElseThrow(EntityNotFoundException::new);
    }

    @Override
    public String deleteIfExists(Integer id) {
        Optional<Product> product = productRepo.findById(id.longValue());
        product.ifPresent(productRepo::delete);
        return "Product successfully deleted";
    }
}

