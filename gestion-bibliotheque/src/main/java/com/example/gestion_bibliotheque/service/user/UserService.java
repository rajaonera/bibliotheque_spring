package com.example.gestion_bibliotheque.service.user;

import com.example.gestion_bibliotheque.entity.user.User;
import java.util.List;
import java.util.Optional;

public interface UserService {

    User createUser(User user);

    Optional<User> getUserById(Long id);

    List<User> getAllUsers();

    User updateUser(Long id, User updatedUser);

    void deleteUser(Long id);

    void activateUser(Long id);

    void deactivateUser(Long id);
}
