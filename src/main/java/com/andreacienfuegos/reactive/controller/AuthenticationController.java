package com.andreacienfuegos.reactive.controller;

import com.andreacienfuegos.reactive.dto.LoginRequest;
import com.andreacienfuegos.reactive.dto.LoginResponse;
import com.andreacienfuegos.reactive.service.AuthenticationService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    public AuthenticationController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @PostMapping("/login")
    public LoginResponse login(@RequestBody LoginRequest request) {

        return authenticationService.login(request);

    }

}