package com.yonni.raquettelover.service;

import org.springframework.stereotype.Service;

import com.yonni.raquettelover.dto.PlaceDto;

@Service
public interface PlaceService {
    public void addPlace(PlaceDto dto);
}
