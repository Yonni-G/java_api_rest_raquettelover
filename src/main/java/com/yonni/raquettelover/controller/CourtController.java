package com.yonni.raquettelover.controller;

import com.yonni.raquettelover.DTO.CourtDTO;
import com.yonni.raquettelover.entity.Court;
import com.yonni.raquettelover.entity.Place;
import com.yonni.raquettelover.entity.User;
import com.yonni.raquettelover.repository.CourtRepository;
import com.yonni.raquettelover.repository.PlaceRepository;
import com.yonni.raquettelover.repository.UserPlaceRepository;
import com.yonni.raquettelover.repository.UserRepository;
import com.yonni.raquettelover.security.CustomUserDetails;
import com.yonni.raquettelover.security.SecurityUtils;
import com.yonni.raquettelover.service.CourtService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/court") // Sans /api/v1, car context-path déjà /api/v1
@RequiredArgsConstructor
public class CourtController {

    private final CourtService courtService;

    @PreAuthorize("hasRole('ADMIN') or hasRole('GESTIONNAIRE')")
    @PostMapping
    public ResponseEntity<?> addCourt(@RequestBody @Valid CourtDTO dto, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Données invalides");
        }

        courtService.addCourt(dto);

        return ResponseEntity.status(HttpStatus.CREATED).body("Terrain créé avec succès");
    }

}
