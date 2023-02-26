package com.vinsguru.userservice.service;

import com.vinsguru.userservice.dto.UserDto;
import com.vinsguru.userservice.repository.UserRepository;
import com.vinsguru.userservice.util.EntityDtoUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * @author debal
 */
@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public Flux<UserDto> all() {
        return this.userRepository.findAll()
                .map(EntityDtoUtil::toDto);
    }

    public Mono<UserDto> findById(int id) {
        return this.userRepository.findById(id)
                .map(EntityDtoUtil::toDto);
    }

    public  Mono<UserDto> createUser(Mono<UserDto> userDtoMono) {
        return userDtoMono
                .map(EntityDtoUtil::toEntity)
                .flatMap(userRepository::save)
                .map(EntityDtoUtil::toDto);
    }

    public  Mono<UserDto> updateUser(int id, Mono<UserDto> userDtoMono) {
        return findById(id)
                .flatMap(userDto -> userDtoMono.map(EntityDtoUtil::toEntity).doOnNext(e -> e.setId(id)))
                .flatMap(userRepository::save)
                .map(EntityDtoUtil::toDto);
    }

    public Mono<Void> deleteUser(int id) {
        return userRepository.deleteById(id);
    }
}
