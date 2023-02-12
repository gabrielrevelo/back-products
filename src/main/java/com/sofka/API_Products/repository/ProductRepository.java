package com.sofka.API_Products.repository;

import com.sofka.API_Products.collection.Product;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;

public interface ProductRepository extends ReactiveCrudRepository<Product, String> {

    Flux<Product> findAllByIdNotNullOrderByIdAsc(final Pageable page);

}
