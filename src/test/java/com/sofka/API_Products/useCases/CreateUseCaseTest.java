package com.sofka.API_Products.useCases;

import com.sofka.API_Products.collection.Product;
import com.sofka.API_Products.model.ProductDTO;
import com.sofka.API_Products.repository.ProductRepository;
import com.sofka.API_Products.utils.MapperUtils;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.SpyBean;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class CreateUseCaseTest {

    @SpyBean
    private CreateUseCase createUseCase;
    @Autowired
    private MapperUtils mapperUtils;

    @Mock
    ProductRepository productRepository;


    @Test
    public void CreateUseCaseTestPass(){
        Product prod = new Product();
        ProductDTO productDTO =new ProductDTO("000","pelota",100,"url",true,10,50);
        prod.setId(productDTO.getId());


        Mockito.when(productRepository.save(prod))
                .thenReturn(Mono.just(mapperUtils
                        .mapperToProduct(prod.getId())
                        .apply(productDTO)));

        StepVerifier.create(createUseCase.apply(productDTO))
                .equals("000");

    }}
