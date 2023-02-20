package com.sofka.API_Products.useCases;

import com.sofka.API_Products.collection.User;
import com.sofka.API_Products.repository.UserRepository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class FindUserUseCase {
    private UserRepository userRepository;

    public FindUserUseCase(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Mono<User> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }
}
