package com.sofka.API_Products.useCases;

import com.sofka.API_Products.collection.User;
import com.sofka.API_Products.repository.UserRepository;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@Data
public class FindUserByEmailUseCase {
    @Autowired
    private UserRepository userRepository;

    public Mono<User> findUserByEmail(String email){
        return userRepository.findByUsername(email);
    }
}
