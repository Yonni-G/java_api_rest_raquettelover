package com.yonni.raquettelover.service;

import com.yonni.raquettelover.DTO.PlaceDTO;
import com.yonni.raquettelover.security.CustomUserDetails;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Service;

@Service
public interface PlaceService {
    public void addPlace(PlaceDTO dto);
}
