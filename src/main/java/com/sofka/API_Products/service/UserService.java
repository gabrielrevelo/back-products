package com.sofka.API_Products.service;

import com.sofka.API_Products.collection.User;
import com.sofka.API_Products.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;


@Service
public class UserService {
    private UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    public Mono<User> findByUsername(String username) {
        return userRepository.findByUsername(username);

    }
}
