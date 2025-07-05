package com.example.gestion_bibliotheque.service.user.impl;

import com.example.gestion_bibliotheque.entity.user.User;
import com.example.gestion_bibliotheque.repository.user.UserRepository;
import com.example.gestion_bibliotheque.service.user.UserService;
import org.springframework.stereotype.Service;
import com.example.gestion_bibliotheque.enums.UserProfil;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User createUser(User user) {
        user.setActive(true); // Par défaut, l’utilisateur est actif
        return userRepository.save(user);
    }

    @Override
    public Optional<User> getUserById(Long id) {
        return userRepository.findById(id);
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User updateUser(Long id, User updatedUser) {
        return userRepository.findById(id)
                .map(user -> {
                    user.setName(updatedUser.getName());
                    user.setEmail(updatedUser.getEmail());
                    user.setRole(updatedUser.getRole());
                    user.setProfile(updatedUser.getProfile());
                    user.setPassword(updatedUser.getPassword());
                    return userRepository.save(user);
                })
                .orElseThrow(() -> new RuntimeException("Utilisateur non trouvé"));
    }

    @Override
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public void activateUser(Long id) {
        userRepository.findById(id).ifPresent(user -> {
            user.setActive(true);
            userRepository.save(user);
        });
    }

    @Override
    public void deactivateUser(Long id) {
        userRepository.findById(id).ifPresent(user -> {
            user.setActive(false);
            userRepository.save(user);
        });
    }

    @Override
    public Optional<User> authenticate(String email, String password) {
        Optional<User> userOpt = userRepository.findByEmail(email);
        if (userOpt.isPresent()) {
            User user = userOpt.get();
            // Compare mot de passe en clair (à remplacer par un hash + encoder)
            if (user.getPassword().equals(password) && user.getProfile() == UserProfil.BIBLIOTHECAIRE && user.isActive()) {
                return Optional.of(user);
            }
        }
        return Optional.empty();
    }
}
