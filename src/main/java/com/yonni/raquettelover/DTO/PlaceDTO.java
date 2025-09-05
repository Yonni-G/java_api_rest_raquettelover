package com.yonni.raquettelover.dto;

import jakarta.validation.constraints.NotBlank;

public record PlaceDto(
        // pour l'admin, l'id du gestionnaire auquel on veut ajouter le lieu
        Long userId,
        @NotBlank(message = "Le nom est obligatoire")
        String name,

        @NotBlank(message = "L'adresse est obligatoire")
        String address){
}

