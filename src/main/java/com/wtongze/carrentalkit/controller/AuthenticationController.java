package com.wtongze.carrentalkit.controller;

import com.wtongze.carrentalkit.model.AuthenticationRequest;
import com.wtongze.carrentalkit.model.AuthenticationResponse;
import com.wtongze.carrentalkit.security.JsonWebTokenUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ReactiveAuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.ReactiveUserDetailsService;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {

    @Autowired
    private ReactiveUserDetailsService userDetailsService;

    @Autowired
    private JsonWebTokenUtils jwt;

    @PostMapping("/login")
    public Mono<AuthenticationResponse> login(@RequestBody AuthenticationRequest authReq) {
        return userDetailsService.findByUsername(authReq.getUsername()).flatMap(userDetails ->
                Mono.just(new AuthenticationResponse(jwt.createToken(userDetails)))
        );
    }
}
