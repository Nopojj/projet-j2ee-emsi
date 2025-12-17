package com.emsi.gestion_vente.security;

import com.emsi.gestion_vente.entity.Users;
import com.emsi.gestion_vente.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Users user = userRepository.findByLogin(username)
                .orElseThrow(() -> new UsernameNotFoundException("Utilisateur non trouvé : " + username));

        return new org.springframework.security.core.userdetails.User(
                user.getLogin(),
                user.getPass(),  // mot de passe brut (Spring Security le hash automatiquement si tu veux, mais pour le test c'est OK)
                new ArrayList<>()  // authorities (rôles) - vide pour l'instant
        );
    }
}