package com.controller;

import com.dto.UserDto;
import com.model.User;
import com.service.UserService;
import com.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private UserService userService;

    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody UserDto userDTO) {
        User user = new User();
        user.setUsername(userDTO.getUsername());
        user.setPassword(userDTO.getPassword());
        userService.register(user);
        return ResponseEntity.ok("Usuário registrado com sucesso!");
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody UserDto userDTO) {
        User foundUser = userService.findByUsername(userDTO.getUsername());
        if (!userService.passwordMatches(userDTO.getPassword(), foundUser.getPassword())) {
            throw new RuntimeException("Credenciais inválidas.");
        }
        String token = jwtUtil.generateToken(foundUser.getUsername());
        return ResponseEntity.ok(token);
    }
}
