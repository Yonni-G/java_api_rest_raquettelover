package com.yonni.raquettelover.dto;

import jakarta.validation.constraints.NotNull;

//@JsonInclude(JsonInclude.Include.NON_NULL)
public record ParticipationPlayerDto(
        @NotNull(message = "L'ID de la réservation est obligatoire")
        Long reservationId,
        Long userId // participant de la réservation
) {
}

