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

import com.yonni.raquettelover.dto.ParticipationDto;
import com.yonni.raquettelover.service.ParticipationService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/participation") // Sans /api/v1, car context-path déjà /api/v1
@RequiredArgsConstructor
public class ParticipationController {

    private final ParticipationService participationService;

    @PostMapping
    @PreAuthorize("hasRole('ADMIN') or #dto.userId == principal.id")
    public ResponseEntity<?> addParticipation(
            @RequestBody @Valid ParticipationDto dto,
            BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Données invalides");
        }

        participationService.addParticipation(dto);

        return ResponseEntity.status(HttpStatus.CREATED).body("Réservation créée avec succès");
    }

}
