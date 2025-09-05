package com.yonni.raquettelover.dto;

import jakarta.validation.constraints.NotNull;
//@JsonInclude(JsonInclude.Include.NON_NULL)
public record ParticipationGuestDto(
        @NotNull(message = "Les informations de l'invité sont obligatoires")
        GuestDto guest,
        @NotNull(message = "L'ID de la réservation est obligatoire")
        Long reservationId,
        Long userId // participant à la réservation
) {
}

