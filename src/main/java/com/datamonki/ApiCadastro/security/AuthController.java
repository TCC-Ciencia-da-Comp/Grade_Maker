package com.datamonki.ApiCadastro.security;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import lombok.Getter;
import lombok.Setter;


@RestController
@RequestMapping("/auth")
public class AuthController {
    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;

    public AuthController(AuthenticationManager authenticationManager, JwtUtil jwtUtil) {
        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;
    }

    @PostMapping("/login")
    public void login(@RequestBody AuthRequest authRequest, HttpServletResponse response) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword())
        );

        // Gera o token JWT
        String jwt = jwtUtil.generateToken(authentication.getName());

        // Cria o cookie HTTP-only com o token
        Cookie cookie = new Cookie("token", jwt);
        cookie.setHttpOnly(true); // Torna o cookie inacessível via JavaScript
        cookie.setSecure(false);  // Requer HTTPS (desativar para testes locais, mas usar em produção)
        cookie.setPath("/");     // Disponível em toda a aplicação
        cookie.setMaxAge(10 * 60 * 60); // 10 horas

        // Adiciona o cookie na resposta
        response.addCookie(cookie);
    }

    @PostMapping("/logout")
    public void logout(HttpServletResponse response) {
        // Remove o cookie JWT no logout
        Cookie cookie = new Cookie("token", null);
        cookie.setHttpOnly(true);
        cookie.setSecure(false);
        cookie.setPath("/");
        cookie.setMaxAge(0); // Expira imediatamente

        response.addCookie(cookie);
    }
}

@Getter
@Setter
class AuthRequest {
    private String username;
    private String password;
}
