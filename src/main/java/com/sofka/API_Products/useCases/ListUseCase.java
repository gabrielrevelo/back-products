package com.sofka.API_Products.useCases;

import com.sofka.API_Products.model.ProductDTO;
import com.sofka.API_Products.repository.ProductRepository;
import com.sofka.API_Products.utils.MapperUtils;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.function.Supplier;

@Service
@Validated
public class ListUseCase {

    private final ProductRepository productRepository;
    private final MapperUtils mapperUtils;

    public ListUseCase(MapperUtils mapperUtils, ProductRepository productRepository) {
        this.productRepository = productRepository;
        this.mapperUtils = mapperUtils;
    }

    public Flux<ProductDTO> getAll() {
        return productRepository.findAll()
                .map(mapperUtils.mapEntityToProduct());
    }

    public Flux<ProductDTO> get(int page) {
        return productRepository.findAllByIdNotNullOrderByIdAsc(PageRequest.of(page, 6))
                .map(mapperUtils.mapEntityToProduct());
    }

    public Mono<Integer> getTotalPages() {
        return productRepository.count().map(count -> (int) Math.ceil(count/6)+1);
    }

}
