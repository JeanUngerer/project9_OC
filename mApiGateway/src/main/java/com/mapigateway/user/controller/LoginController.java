package com.mapigateway.user.controller;


import com.mapigateway.user.service.TokenService;
import com.mapigateway.user.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
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




    @GetMapping("/home")
    public String homeSweetHome(){
        return "Hi home !";
    }

    @GetMapping("/secured")
    public String homeSecuredHome(){
        return "Hi secured home !";
    }
}
