package com.sofka.API_Products.routers;

import com.sofka.API_Products.repository.ProductRepository;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import reactor.core.publisher.Mono;

import java.util.Objects;

@Service
@Validated
public class DeleteUseCase {
    private final ProductRepository productRepository;

    public DeleteUseCase(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Mono<Void> apply(String id) {
        Objects.requireNonNull(id, "Id is required");
        return productRepository.deleteById(id);
    }
}
