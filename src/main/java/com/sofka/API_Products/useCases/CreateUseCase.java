package com.sofka.API_Products.useCases;

import com.sofka.API_Products.collection.Product;
import com.sofka.API_Products.model.ProductDTO;
import com.sofka.API_Products.repository.ProductRepository;
import com.sofka.API_Products.utils.MapperUtils;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import reactor.core.publisher.Mono;

import javax.validation.Valid;

@Service
@Validated
public class CreateUseCase {
    private final ProductRepository productRepository;

    private final MapperUtils mapperUtils;

    public CreateUseCase(MapperUtils mapperUtils, ProductRepository productRepository) {
        this.productRepository = productRepository;
        this.mapperUtils = mapperUtils;
    }

    public Mono<Product> apply(@Valid ProductDTO newProduct) {
        return productRepository
                .save(mapperUtils.mapperToProduct(null).apply(newProduct));
    }

}
