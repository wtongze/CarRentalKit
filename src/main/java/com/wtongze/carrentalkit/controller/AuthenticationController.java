package com.wtongze.carrentalkit.controller;

import com.wtongze.carrentalkit.model.AuthenticationRequest;
import com.wtongze.carrentalkit.model.AuthenticationResponse;
import com.wtongze.carrentalkit.security.JsonWebTokenUtils;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.ReactiveUserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@Tag(
        name = "Authentication Controller",
        description = "Allowing users login and get their JWT"
)
@RestController
@RequestMapping("/auth")
public class AuthenticationController {

    @Autowired
    private ReactiveUserDetailsService userDetailsService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JsonWebTokenUtils jwt;

    @PostMapping("/login")
    public Mono<ResponseEntity<AuthenticationResponse>> login(@RequestBody AuthenticationRequest authReq) {
        return userDetailsService.findByUsername(authReq.getUsername()).flatMap(user -> {
            if (passwordEncoder.encode(authReq.getPassword()).equals(user.getPassword()) &&
                    user.isAccountNonExpired() &&
                    user.isAccountNonLocked() &&
                    user.isCredentialsNonExpired()) {
                String token = jwt.createToken(user);
                return Mono.just(ResponseEntity.ok(new AuthenticationResponse(token)));
            } else {
                return Mono.just(ResponseEntity.status(HttpStatus.UNAUTHORIZED).build());
            }
        });
    }
}
