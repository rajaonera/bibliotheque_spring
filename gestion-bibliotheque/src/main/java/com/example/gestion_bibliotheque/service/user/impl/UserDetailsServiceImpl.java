package com.example.gestion_bibliotheque.service.user.impl;

import com.example.gestion_bibliotheque.entity.user.User;
import com.example.gestion_bibliotheque.repository.user.UserRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;

    public UserDetailsServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("Utilisateur non trouvé avec email : " + email));

        List<GrantedAuthority> authorities = new ArrayList<>();
        // Ajoute le rôle de l'utilisateur en préfixant par "ROLE_"
        authorities.add(new SimpleGrantedAuthority("ROLE_" + user.getRole().getName()));

        // Retourne un UserDetails Spring Security avec email, password et rôles
        return new org.springframework.security.core.userdetails.User(
                user.getEmail(),
                user.getPassword(),
                authorities
        );
    }
}
