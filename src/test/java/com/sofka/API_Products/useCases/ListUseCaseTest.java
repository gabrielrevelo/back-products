package com.sofka.API_Products.useCases;

import com.sofka.API_Products.collection.Product;
import com.sofka.API_Products.repository.ProductRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.SpyBean;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ListUseCaseTest {
    @SpyBean
    ListUseCase listUseCase;

    @Mock
    ProductRepository productRepository;


    void Get() {
        Product product = new Product("000", "pelota", 100, "url", true, 10, 50);

        Mockito.when(productRepository.findAll()).thenReturn(Flux.just(product));

        StepVerifier.create(listUseCase.getAll()).expectNextMatches(mono -> {
            assert mono.getId().equals("000");
            assert mono.getName().equals("pelota");
            assert mono.getInventory().equals(100);
            assert mono.getImageUrl().equals("url");
            assert mono.getEnabled().equals(true);
            assert mono.getMin().equals(10);
            assert mono.getMax().equals(50);
            return true;
        }).verifyComplete();
    }

}
