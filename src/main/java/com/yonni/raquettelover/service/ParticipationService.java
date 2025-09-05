package com.yonni.raquettelover.service;

import com.yonni.raquettelover.DTO.ParticipationDTO;
import com.yonni.raquettelover.DTO.ReservationDTO;
import org.springframework.stereotype.Service;

@Service
public interface ParticipationService {
    public void addParticipation(ParticipationDTO dto);
}
