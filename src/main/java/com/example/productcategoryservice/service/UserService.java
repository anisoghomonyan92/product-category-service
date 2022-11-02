package com.example.productcategoryservice.service;

import com.example.productcategoryservice.model.User;
import com.example.productcategoryservice.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public User save(User map) {
        return userRepository.save(map);
    }
}
