package com.backend.service.impl;

import com.backend.entities.Demande;
import com.backend.entities.User;
import com.backend.repositories.UserRepository;
import com.backend.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceIMPL implements UserService {
    //
    private final UserRepository userRepository;
    //

    @Override
    public Long createUser(User user) {
        return this.userRepository.save(user).getId();
    }

    @Override
    public List<User> getAllUsers() {
        return this.userRepository.findAll();
    }

    @Override
    public Long editUser(User user, Long id) {
        //
        Optional<User> userAMettreAjourOptional = null;

        userAMettreAjourOptional = this.userRepository.findById(id);

        if (userAMettreAjourOptional.isPresent()) {

            User userAMettreAjour = userAMettreAjourOptional.get();

            userAMettreAjour.setEmail(user.getEmail());
            userAMettreAjour.setPassword(user.getPassword());
            return this.userRepository.save(userAMettreAjour).getId();
        } else {
            return Long.valueOf(0);
        }
        //
    }

    @Override
    public void deleteUser(Long id) {
        this.userRepository.deleteById(id);
    }

    @Override
    public User getOneUser(Long id) {
        Optional<User> userOptional = this.userRepository.findById(id);
        return userOptional.get();
    }
}
