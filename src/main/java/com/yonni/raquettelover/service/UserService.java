package com.yonni.raquettelover.service;

import com.yonni.raquettelover.entity.User;

public interface UserService {
    User loadUserFromDTOorPrincipal(Long userId);
}
