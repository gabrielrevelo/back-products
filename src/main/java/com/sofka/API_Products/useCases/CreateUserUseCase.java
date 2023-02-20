package com.sofka.API_Products.useCases;

import com.sofka.API_Products.collection.User;
import com.sofka.API_Products.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class CreateUserUseCase {
    private final UserRepository userRepository;
    public Mono<User> apply(User user) {
        return userRepository
                .save(user);
    }
}
