package com.yonni.raquettelover.service;

import org.springframework.stereotype.Service;

import com.yonni.raquettelover.dto.ParticipationDto;

@Service
public interface ParticipationService {
    public void addParticipation(ParticipationDto dto);
}
