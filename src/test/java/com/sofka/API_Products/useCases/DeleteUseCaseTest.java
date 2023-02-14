package com.sofka.API_Products.useCases;

import com.sofka.API_Products.collection.Product;
import com.sofka.API_Products.model.ProductDTO;
import com.sofka.API_Products.repository.ProductRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.SpyBean;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class DeleteUseCaseTest {
    @SpyBean
    private DeleteUseCase deleteUseCase;
    @Mock
    ProductRepository productRepository;

    @Test
    public void Delete() {
        Product product = new Product("000","pelota",100,"url",true,10,50);

        Mockito.when(productRepository.deleteById(product.getId())).thenReturn(Mono.empty());


        StepVerifier
                .create(deleteUseCase.apply(product.getId()))
                .expectNext()
                .verifyComplete();
    }
}
