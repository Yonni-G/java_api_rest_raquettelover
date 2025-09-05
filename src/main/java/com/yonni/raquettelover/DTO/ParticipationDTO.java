package com.yonni.raquettelover.dto;

import jakarta.validation.constraints.NotNull;
//@JsonInclude(JsonInclude.Include.NON_NULL)
public record ParticipationDto(
        String name,
        String email,
        String phone,
        @NotNull(message = "L'ID de la réservation est obligatoire")
        Long reservationId,
        Long userId
) {
}

