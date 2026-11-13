package com.example.security.oauth2.service;

import com.example.security.oauth2.config.AppProperties;
import com.example.security.oauth2.entity.Role;
import com.example.security.oauth2.entity.User;
import com.example.security.oauth2.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final AppProperties appProperties;

    @Transactional
    public User findOrCreate(String email) {
        Role role = resolveRole(email);
        return userRepository.findByUsername(email)
                .map(existing -> {
                    if (existing.getRole() != role) {
                        existing.setRole(role);
                    }
                    return existing;
                })
                .orElseGet(() -> userRepository.save(new User(email, role)));
    }

    private Role resolveRole(String email) {
        if (appProperties.getAdminEmails().stream()
                .anyMatch(adminEmail -> adminEmail.equalsIgnoreCase(email))) {
            return Role.ADMIN;
        }
        return Role.USER;
    }
}
