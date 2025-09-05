package com.yonni.raquettelover.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.yonni.raquettelover.dto.PlaceDto;
import com.yonni.raquettelover.service.PlaceService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/place") // Sans /api/v1, car context-path déjà /api/v1
@RequiredArgsConstructor
public class PlaceController {
    
    private final PlaceService placeService;

    @PreAuthorize("hasRole('ADMIN') or hasRole('GESTIONNAIRE')")
    @PostMapping
    public ResponseEntity<?> addPlace(@RequestBody @Valid PlaceDto dto,
                                      BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Données invalides");
        }
        placeService.addPlace(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body("Lieu créé avec succès");
    }

}
