package com.yonni.raquettelover.service;

import org.springframework.stereotype.Service;

import com.yonni.raquettelover.dto.PlaceDto;
import com.yonni.raquettelover.entity.Place;
import com.yonni.raquettelover.entity.User;
import com.yonni.raquettelover.entity.UserPlace;
import com.yonni.raquettelover.repository.PlaceRepository;
import com.yonni.raquettelover.repository.UserPlaceRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PlaceServiceImpl implements PlaceService {

    private final PlaceRepository placeRepository;
    private final UserService userService;
    private final UserPlaceRepository userPlaceRepository;

    @Override
    public void addPlace(PlaceDto dto) {

        Place place = new Place();
        place.setName(dto.name());
        place.setAddress(dto.address());
        Place placeAdded = placeRepository.save(place);

        User user = userService.loadUserFromDTOorPrincipal(dto.userId());

        userPlaceRepository.save(new UserPlace(user, placeAdded));
    }

}
