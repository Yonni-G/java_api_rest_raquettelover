package com.yonni.raquettelover.DTO;

import com.yonni.raquettelover.enumeration.CourtType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CourtDTO(
        @NotBlank(message = "Le nom est obligatoire")
        String name,
        @NotBlank(message = "La description est obligatoire")
        String description,
        CourtType type,
        @NotNull(message = "L'id du lieu est obligatoire")
        Long placeId) {

}

