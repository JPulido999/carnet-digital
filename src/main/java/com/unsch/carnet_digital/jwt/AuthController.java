package com.unsch.carnet_digital.jwt;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.*;

import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@RestController
public class AuthController {
    private final JwtService jwtService;

    public AuthController(JwtService jwtService) {
        this.jwtService = jwtService;
    }

    @GetMapping("/loginSuccess")
    public void loginSuccess(@AuthenticationPrincipal OAuth2User user, HttpServletResponse response) throws IOException {
        String email = user.getAttribute("email");
        String token = jwtService.generarToken(email);

        // Redirigir al frontend con el token
        response.sendRedirect("http://localhost:5173/?token=" + token);
    }

}
