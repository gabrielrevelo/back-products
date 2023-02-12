package com.sofka.API_Products.routers;

import com.sofka.API_Products.model.ProductDTO;
import com.sofka.API_Products.useCases.CreateUseCase;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springdoc.core.annotations.RouterOperation;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import java.util.function.Function;

import static org.springframework.web.reactive.function.server.RequestPredicates.*;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration
public class ProductRouter {

    @Bean
    @RouterOperation(
            path = "/create",
            produces = {
                    MediaType.APPLICATION_JSON_VALUE
            },
            method = RequestMethod.POST,
            beanClass = CreateUseCase.class,
            beanMethod = "apply",
            operation = @Operation(
                    operationId = "apply",
                    responses = {
                            @ApiResponse(
                                    responseCode = "200",
                                    description = "Producto creado",
                                    content = @Content(schema = @Schema(
                                            implementation = ProductDTO.class
                                    )
                                    )
                            )
                    }
            )
    )
    public RouterFunction<ServerResponse> create(CreateUseCase createUseCase) {
        Function<ProductDTO, Mono<ServerResponse>> executor = productDTO -> createUseCase.apply(productDTO)
                .flatMap(result -> ServerResponse.ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .bodyValue(result));

        return route(
                POST("/create").and(accept(MediaType.APPLICATION_JSON)),
                request -> request.bodyToMono(ProductDTO.class).flatMap(executor)
        );
    }

    @Bean
    @RouterOperation(
            path = "/list/{page}",
            produces = {
                    MediaType.APPLICATION_JSON_VALUE
            },
            method = RequestMethod.GET,
            beanClass = ListUseCase.class,
            beanMethod = "get",
            operation = @Operation(
                    operationId = "get",
                    responses = {
                            @ApiResponse(
                                    responseCode = "200",
                                    description = "Lista de productos",
                                    content = @Content(array =
                                    @ArraySchema(schema = @Schema(
                                            implementation = ProductDTO.class)
                                    )
                                    )
                            )
                    },
                    parameters = {
                            @Parameter(in = ParameterIn.PATH,name = "page")
                    }
            )
    )
    public RouterFunction<ServerResponse> getAll(ListUseCase listUseCase) {
        return route(GET("/list/{page}"),
                request -> ServerResponse.ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(BodyInserters.fromPublisher(listUseCase.get(
                                Integer.parseInt(request.pathVariable("page"))
                        ), ProductDTO.class))
        );
    }

    @Bean
    public RouterFunction<ServerResponse> get(GetUseCase getUseCase) {
        return route(
                GET("/get/{id}").and(accept(MediaType.APPLICATION_JSON)),
                request -> ServerResponse.ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(BodyInserters.fromPublisher(getUseCase.apply(
                                        request.pathVariable("id")),
                                ProductDTO.class
                        ))
        );
    }

    @Bean
    @RouterOperation(
            path = "/delete/{id}",
            produces = {
                    MediaType.APPLICATION_JSON_VALUE
            },
            method = RequestMethod.DELETE,
            beanClass = DeleteUseCase.class,
            beanMethod = "apply",
            operation = @Operation(
                    operationId = "apply",
                    responses = {
                            @ApiResponse(
                                    responseCode = "200",
                                    description = "successful operation",
                                    content = @Content(schema = @Schema(
                                            implementation = ProductDTO.class
                                    ))
                            ),
                            @ApiResponse(responseCode = "404",description = "customer not found with given id")
                    },
                    parameters = {
                            @Parameter(in = ParameterIn.PATH,name = "id")
                    }
            )
    )
    public RouterFunction<ServerResponse> delete(DeleteUseCase deleteUseCase) {
        return route(
                DELETE("/delete/{id}").and(accept(MediaType.APPLICATION_JSON)),
                request -> ServerResponse.accepted()
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(BodyInserters.fromPublisher(deleteUseCase.apply(request.pathVariable("id")), Void.class))
        );
    }
}
