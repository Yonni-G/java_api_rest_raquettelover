package com.yonni.raquettelover.DTO;

import com.yonni.raquettelover.enumeration.CourtType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record PlaceDTO(
        // pour l'admin, l'id du gestionnaire auquel on veut ajouter le lieu
        Long userId,
        @NotBlank(message = "Le nom est obligatoire")
        String name,

        @NotBlank(message = "L'adresse est obligatoire")
        String address){
}

