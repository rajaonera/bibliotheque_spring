package com.example.gestion_bibliotheque.config;

import com.example.gestion_bibliotheque.entity.user.Role;
import com.example.gestion_bibliotheque.entity.user.User;
import com.example.gestion_bibliotheque.enums.UserProfil;
import com.example.gestion_bibliotheque.repository.user.UserRepository;
import com.example.gestion_bibliotheque.service.user.impl.UserDetailsServiceImpl;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(auth -> auth
                        .anyRequest().permitAll() // 👈 autorise tout sans authentification
                )
                .httpBasic(AbstractHttpConfigurer::disable); // 👈 désactive Basic Auth

        return http.build();
    }
//    UserRepository userRepository;
//
//    @Bean
//    public PasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder();
//    }
//    @Bean
//    public UserDetailsService userDetailsService(UserRepository userRepository) {
//        return new UserDetailsServiceImpl(userRepository);
//    }

//
//    @Bean
//    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//        http
//                .csrf(AbstractHttpConfigurer::disable)
//                .authorizeHttpRequests(auth -> auth.anyRequest().authenticated())
//                .httpBasic(Customizer.withDefaults());
//        return http.build();
//    }
//
//    @Bean
//    public CommandLineRunner createAdminUserIfNotExists(UserRepository userRepository, PasswordEncoder encoder) {
//        return args -> {
//            if (userRepository.findByEmail("admin@gmail.com").isEmpty()) {
//                User admin = new User();
//                admin.setEmail("admin@gmail.com");
//                admin.setName("admin");
//                admin.setPassword(encoder.encode("admin123"));
//                admin.setProfile(UserProfil.BIBLIOTHECAIRE);
//                admin.setRole(new Role(1,"ADMIN"));
//                admin.setActive(true);
//                userRepository.save(admin);
//                System.out.println("Admin user created with email/username=admin@gmail.com and password=admin123");
//            }
//        };
//    }

}
