package com.sofka.API_Products.useCases;

import com.sofka.API_Products.collection.User;
import com.sofka.API_Products.model.UserDTO;
import com.sofka.API_Products.repository.UserRepository;
import com.sofka.API_Products.utils.MapperUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class SaveUserUseCase {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private MapperUtils mapperUtils;

    public Mono<User> saveUser(UserDTO userDTO){
        User user = mapperUtils.mapperToUser().apply(userDTO);
        return userRepository
                .save(user);
    }
}
