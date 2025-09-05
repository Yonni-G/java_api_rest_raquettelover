package com.yonni.raquettelover.dto;

import com.yonni.raquettelover.enumeration.CourtType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record GuestDto(
        String firstName,
        String email,
        String phoneNumber) {
}

