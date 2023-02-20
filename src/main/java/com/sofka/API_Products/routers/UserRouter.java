package com.sofka.API_Products.routers;

import com.sofka.API_Products.collection.User;
import com.sofka.API_Products.model.UserDTO;
import com.sofka.API_Products.useCases.FindUserByEmailUseCase;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.extern.slf4j.Slf4j;
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
@Slf4j
public class UserRouter {

/*    @RouterOperation(
            path = "/saveUser",
            produces ={
                    MediaType.APPLICATION_JSON_VALUE
            },
            method = RequestMethod.POST,
            beanClass = UserRouter.class,
            beanMethod = "saveUser",
            operation = @Operation(
                    operationId = "saveUser",
                    responses = {
                            @ApiResponse(
                                    responseCode = "200",
                                    description = "successful operation",
                                    content = @Content(schema = @Schema(
                                            implementation = User.class
                                    ))
                            )
                    },
                    requestBody = @RequestBody(
                            content = @Content(schema = @Schema(
                                    implementation = UserDTO.class
                            ))
                    )
            )
    )
    @Bean
    public RouterFunction<ServerResponse> saveUser(SaveUserUseCase saveUserUseCase){

        Function<UserDTO, Mono<ServerResponse>> executor = userDTO -> {

            return saveUserUseCase.saveUser(userDTO)
                    .flatMap(result -> {

                        return ServerResponse.ok()
                                .contentType(MediaType.APPLICATION_JSON)
                                .bodyValue(result);
                    });
        };*/


/*
        return route(
                POST("/saveUser").and(accept(MediaType.APPLICATION_JSON)),
                request -> request.bodyToMono(UserDTO.class).flatMap(executor)
        );
    }
*/

    @RouterOperation(
            path = "/findUser/{email}",
            produces ={
                    MediaType.APPLICATION_JSON_VALUE
            },
            method = RequestMethod.GET,
            beanClass = UserRouter.class,
            beanMethod = "findUserByEmail",
            operation = @Operation(
                    operationId = "findUserByEmail",
                    responses = {
                            @ApiResponse(
                                    responseCode = "200",
                                    description = "successful operation",
                                    content = @Content(schema = @Schema(
                                            implementation = User.class
                                    ))
                            )
                    },
                    parameters = {
                            @Parameter(in = ParameterIn.PATH, name = "email")
                    }
            )
    )
    @Bean
    public RouterFunction<ServerResponse> findUserByEmail(FindUserByEmailUseCase findUserByEmailUseCase){
        return route(
                GET("/findUser/{email}"),
                request -> {
                    String email = request.pathVariable("email");

                    return ServerResponse.ok()
                            .contentType(MediaType.APPLICATION_JSON)
                            .body(BodyInserters.fromPublisher(
                                    findUserByEmailUseCase.findUserByEmail(email),
                                    User.class
                            ));
                }
        );
    }
}
