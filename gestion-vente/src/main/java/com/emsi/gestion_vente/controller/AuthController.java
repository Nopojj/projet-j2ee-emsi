package com.emsi.gestion_vente.controller;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;  // ✅ Bon import
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.emsi.gestion_vente.security.JwtUtil;

 @Controller  
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;

    public AuthController(AuthenticationManager authenticationManager, JwtUtil jwtUtil) {
        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;
    }

    /**
     * Page de login
     */
    @GetMapping("/login")
    public String showLoginPage(@RequestParam(value = "error", required = false) String error,
                                @RequestParam(value = "logout", required = false) String logout,
                                Model model) {
        if (error != null) {
            model.addAttribute("error", "Identifiants incorrects");
        }
        if (logout != null) {
            model.addAttribute("message", "Déconnexion réussie");
        }
        return "auth-login";  
    }

    /**
     * API REST pour l'authentification (retourne un token JWT)
     */
    @PostMapping("/api/auth/login")
    @ResponseBody
    public AuthResponse authenticate(@RequestBody AuthRequest request) {
        try {
            Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getLogin(), request.getPass())
            );
            
            String token = jwtUtil.generateToken(request.getLogin());
            return new AuthResponse(token, "Authentification réussie");
            
        } catch (AuthenticationException e) {
            throw new RuntimeException("Login ou mot de passe incorrect");
        }
    }

    // DTOs pour l'API REST
    public static class AuthRequest {
        private String login;
        private String pass;

        public String getLogin() { return login; }
        public void setLogin(String login) { this.login = login; }

        public String getPass() { return pass; }
        public void setPass(String pass) { this.pass = pass; }
    }

    public static class AuthResponse {
        private String token;
        private String message;

        public AuthResponse(String token, String message) {
            this.token = token;
            this.message = message;
        }

        public String getToken() { return token; }
        public String getMessage() { return message; }
    }
}