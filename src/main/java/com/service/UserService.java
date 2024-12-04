package com.service;

import com.model.User;
import com.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public void register(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword())); // Encripta a senha
        userRepository.save(user); // Salva o usuário no banco
    }
    public User findByUsername(String username) {
        return userRepository.findByUsername(username)
                             .orElseThrow(() -> new RuntimeException("Usuário não encontrado."));
    }
    public boolean passwordMatches(String rawPassword, String encodedPassword) {
        return passwordEncoder.matches(rawPassword, encodedPassword);
    }
}