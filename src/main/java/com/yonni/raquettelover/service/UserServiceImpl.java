package com.yonni.raquettelover.service;

import com.yonni.raquettelover.entity.User;
import com.yonni.raquettelover.repository.UserRepository;
import com.yonni.raquettelover.security.CustomUserDetails;
import com.yonni.raquettelover.security.SecurityUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public User loadUserFromDTOorPrincipal(Long userId) {
        CustomUserDetails principal = SecurityUtils.getCurrentUser();
        if (SecurityUtils.isAdmin(principal)) {
            if (userId == null) {
                throw new IllegalArgumentException("L'ID utilisateur est obligatoire pour un admin");
            }
            return userRepository.findById(userId)
                    .orElseThrow(() -> new IllegalArgumentException("Utilisateur non trouvé"));
        } else {
            return userRepository.findById(principal.getId())
                    .orElseThrow(() -> new IllegalStateException("Utilisateur non authentifié"));
        }
    }
}

