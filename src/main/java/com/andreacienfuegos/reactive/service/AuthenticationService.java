package com.andreacienfuegos.reactive.service;

import com.andreacienfuegos.reactive.dto.LoginRequest;
import com.andreacienfuegos.reactive.dto.LoginResponse;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {

    private final AuthenticationManager authenticationManager;

    public AuthenticationService(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    public LoginResponse login(LoginRequest request) {

        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );

        // De momento devolvemos un texto fijo.
        // Después aquí devolveremos un JWT real.
        return new LoginResponse("Login correcto");
    }
}