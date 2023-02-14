package com.sofka.API_Products.useCases;

import com.sofka.API_Products.collection.Product;
import com.sofka.API_Products.model.ProductDTO;
import com.sofka.API_Products.repository.ProductRepository;
import com.sofka.API_Products.utils.MapperUtils;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.SpyBean;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class UpdateUseCaseTest {
    @SpyBean
    UpdateUseCase updateUseCase;

    @Mock
    private ProductRepository productRepository;

    @Autowired
    private MapperUtils mapperUtils;

    @Test
    void Update() {
        Product product = new Product("000", "pelota", 100, "url", true, 10, 50);
        ProductDTO productDTO = new ProductDTO("000", "pelota", 100, "url", true, 10, 50);

        Mockito.when(productRepository.save(product)).thenReturn(Mono.just(product));

        StepVerifier.create(updateUseCase.apply(productDTO)).expectNextMatches(mono -> {
            assert mono.equals("000");
            return true;
        }).verifyComplete();
    }

}
