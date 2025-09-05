package com.yonni.raquettelover.service;

import com.yonni.raquettelover.DTO.CourtDTO;
import com.yonni.raquettelover.DTO.ReservationDTO;
import org.springframework.stereotype.Service;

@Service
public interface ReservationService {
    public void addReservation(ReservationDTO dto);
}
