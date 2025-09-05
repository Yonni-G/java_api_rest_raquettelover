package com.yonni.raquettelover.service;

import com.yonni.raquettelover.DTO.CourtDTO;
import com.yonni.raquettelover.DTO.PlaceDTO;
import org.springframework.stereotype.Service;

@Service
public interface CourtService {
    public void addCourt(CourtDTO dto);
}
