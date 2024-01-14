package com.mapigateway.user.repository;

import com.mapigateway.user.entity.UserEntity;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

import java.util.Optional;


@Repository
public interface UserRepository extends ReactiveMongoRepository<UserEntity, String> {

    Mono<UserEntity> getByUserName(String userName);

    Mono<UserEntity> getByEmail(String email);
}

