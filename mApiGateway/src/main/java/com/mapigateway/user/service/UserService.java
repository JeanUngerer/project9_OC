package com.mapigateway.user.service;


import com.mapigateway.exception.ExceptionHandler;
import com.mapigateway.user.entity.UserEntity;
import com.mapigateway.user.mapper.UserMapper;
import com.mapigateway.user.model.MyUser;
import com.mapigateway.user.repository.UserRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.*;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
@Transactional
public class UserService implements ReactiveUserDetailsService {


    PasswordEncoder passwordEncoder;
    UserRepository userRepository;
    UserMapper userMapper;
/*
    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        UserEntity userEntity = userRepository.findByUserName(userName).orElseThrow(() -> new UsernameNotFoundException("No user with that username"));
        MyUser myUser = userMapper.entityToModel(userEntity);
        List<SimpleGrantedAuthority> authi = new ArrayList<>();
        authi.add(new SimpleGrantedAuthority(myUser.getRoles()));
        return new User(myUser.getUserName(), myUser.getPassword(), authi);
    }
*/
/*

    public List<MyUser> getUsers() {
        try {
            log.info("getUsers");
            List<UserEntity> users = userRepository.findAll();
            return userMapper.entitiesToModel(users);
        } catch (Exception e) {
            log.error("Couldn't find all user: " + e.getMessage());
            throw new ExceptionHandler("We could not find your users");

        }
    }
*/


    public Mono<UserEntity> createUser(MyUser myUser) {
        try {
            log.info("create user - userDTO: " + myUser.toString());

            String pass = passwordEncoder.encode(myUser.getPassword());

            myUser.setPassword(pass);
            myUser.set_id(null);
            log.info("create user - userDTO: " + myUser.toString());


            Mono<UserEntity> user = userRepository.insert(userMapper.modelToEntity(myUser));
            Flux<UserEntity> userEntityFlux = userRepository.findAll();

            log.info("create user - userDTO: " );

            return user;
            //myUser = userMapper.entityToModel(userRepository.findByUserName(myUser.getUserName()).get());
            //return myUser;
        } catch (Exception e) {
            log.error("Couldn't create user: " + e.getMessage());
            throw new ExceptionHandler("We could not create your account ;; " + e);
        }
    }


/*
    public MyUser getUserById(Long id) {
        try {
            log.info("getUserById - id: " + id.toString());
            return userMapper.entityToModel(userRepository.findById(id));//.orElseThrow(()
               //     -> new ExceptionHandler("We could not find your account")));
        } catch (Exception e) {
            log.error("Couldn't find user: " + e.getMessage());
            throw new ExceptionHandler("We could not find your account");
        }
    }
*/
    /*
    public MyUser getUserByEmail(String email) {
        try {
            log.info("getUserByEmail - email: " + email);
            return userMapper.entityToModel(userRepository.findByEmail(email).orElseThrow(()
                    -> new ExceptionHandler("We could not find your account")));
        } catch (Exception e) {
            log.error("Couldn't find user: " + e.getMessage());
            throw new ExceptionHandler("We could not find your account");
        }
    }
*/
    /*
    public MyUser getUserByUserName(String userName) {
        try {
            log.info("getUserByUserName - userName: " + userName);
            MyUser mee =userMapper.entityToModel(userRepository.findByUserName(userName).orElseThrow(()
                    -> new ExceptionHandler("We could not find your account")));
            return userMapper.entityToModel(userRepository.findByUserName(userName).orElseThrow(()
                    -> new ExceptionHandler("We could not find your account")));
        } catch (Exception e) {
            log.error("Couldn't find user: " + e.getMessage());
            throw new ExceptionHandler("We could not find your account");
        }
    }
*/
    /*
    public String deleteUser(Long id) {
        try {
            log.info("deleteUser - id: " + id.toString());
            UserEntity userEntity = userRepository.findById(id).orElseThrow(()
                    -> new ExceptionHandler("We could not find your account"));
            userRepository.delete(userEntity);
            return "Contact deleted";
        } catch (Exception e) {
            log.error("Couldn't delete user: " + e.getMessage());
            throw new ExceptionHandler("We could not delete your account");
        }
    }
*/


    @Override
    public Mono<UserDetails> findByUsername(String userName) {
        /*
        UserEntity userEntity = userRepository.findByUserName(userName).orElseThrow(() -> new UsernameNotFoundException("No user with that username"));
        MyUser myUser = userMapper.entityToModel(userEntity);
        List<SimpleGrantedAuthority> authi = new ArrayList<>();
        authi.add(new SimpleGrantedAuthority(myUser.getRoles()));

         */
        return userRepository.getByUserName(userName)
                .map(user -> User
                        .withUsername(user.getUserName())
                        .password(user.getPassword())
                        .authorities(user.getRoles()) // Add any roles or authorities here
                        .accountExpired(false)
                        .accountLocked(false)
                        .credentialsExpired(false)
                        .disabled(false)
                        .build())
                .cast(UserDetails.class);
    }
}
