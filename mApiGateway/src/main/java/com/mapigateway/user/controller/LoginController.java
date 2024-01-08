package com.mapigateway.user.controller;


import com.mapigateway.user.model.MyUser;
import com.mapigateway.user.service.TokenService;
import com.mapigateway.user.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import reactor.core.Disposable;

import java.util.concurrent.atomic.AtomicReference;

@RestController
@Slf4j
@RequestMapping("api")
public class LoginController {

    private final TokenService tokenService;

    private final UserService userService;

    public LoginController(TokenService tokenService, UserService userService) {
        this.tokenService = tokenService;
        this.userService = userService;
    }


    @PostMapping("/token")
    public ResponseEntity<Void> token(Authentication authentication) {
        log.debug("Token requested for user : " + authentication.getName());
        String token = tokenService.generateToken(authentication);
        log.debug("Token granted : " + token);

        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.set("Token", token);

        return ResponseEntity.ok()
                .headers(responseHeaders)
                .build();
    }

    @PostMapping("/register")
    @Transactional
    public ResponseEntity<Void> registerUser(@RequestBody MyUser userDto) {

        AtomicReference<Boolean> created = new AtomicReference<>(true);

        Disposable disposable = userService.createUser(userDto).subscribe(item -> {
            if(item == null){
                created.set(false);
            }

            log.debug("registered user : " + item);
        });
        disposable.dispose();

        if(created.get()){
            return ResponseEntity.ok().build();
        }

        return (ResponseEntity<Void>) ResponseEntity.badRequest();


    }

    @GetMapping("/home")
    public String homeSweetHome(){
        return "Hi home !";
    }

    @GetMapping("/secured")
    public String homeSecuredHome(){
        return "Hi secured home !";
    }
}
