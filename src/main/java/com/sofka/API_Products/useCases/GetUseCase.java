package com.sofka.API_Products.useCases;

import com.sofka.API_Products.model.ProductDTO;
import com.sofka.API_Products.repository.ProductRepository;
import com.sofka.API_Products.utils.MapperUtils;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import reactor.core.publisher.Mono;

import java.util.Objects;
import java.util.function.Function;

@Service
@Validated
public class GetUseCase implements Function<String, Mono<ProductDTO>> {

    private final ProductRepository productRepository;

    private final MapperUtils mapperUtils;

    public GetUseCase(MapperUtils mapperUtils, ProductRepository productRepository) {
        this.productRepository = productRepository;
        this.mapperUtils = mapperUtils;
    }

    @Override
    public Mono<ProductDTO> apply(String id) {
        Objects.requireNonNull(id, "Id is required");
        return productRepository.findById(id)
                .map(mapperUtils.mapEntityToProduct());
    }
}
