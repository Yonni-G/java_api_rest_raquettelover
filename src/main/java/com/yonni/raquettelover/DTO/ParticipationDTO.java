package com.yonni.raquettelover.DTO;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
//@JsonInclude(JsonInclude.Include.NON_NULL)
public record ParticipationDTO(
        String name,
        String email,
        String phone,
        @NotNull(message = "L'ID de la réservation est obligatoire")
        Long reservationId,
        Long userId
) {
}

