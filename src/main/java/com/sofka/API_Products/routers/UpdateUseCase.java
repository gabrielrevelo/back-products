package com.sofka.API_Products.routers;

import com.sofka.API_Products.collection.Product;
import com.sofka.API_Products.model.ProductDTO;
import com.sofka.API_Products.repository.ProductRepository;
import com.sofka.API_Products.utils.MapperUtils;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import reactor.core.publisher.Mono;

import java.util.Objects;

@Service
@Validated
public class UpdateUseCase {
    private final ProductRepository productRepository;

    private final MapperUtils mapperUtils;

    public UpdateUseCase(ProductRepository productRepository, MapperUtils mapperUtils) {
        this.productRepository = productRepository;
        this.mapperUtils = mapperUtils;
    }

    public Mono<String> apply(ProductDTO dto) {
        Objects.requireNonNull(dto.getId(), "Id of the question is required");
        return productRepository
                .save(mapperUtils.mapperToProduct(dto.getId()).apply(dto))
                .map(Product::getId);
    }
}
