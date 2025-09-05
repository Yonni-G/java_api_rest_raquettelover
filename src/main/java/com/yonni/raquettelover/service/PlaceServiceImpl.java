package com.yonni.raquettelover.service;

import com.yonni.raquettelover.DTO.PlaceDTO;
import com.yonni.raquettelover.entity.Place;
import com.yonni.raquettelover.entity.User;
import com.yonni.raquettelover.entity.UserPlace;
import com.yonni.raquettelover.repository.PlaceRepository;
import com.yonni.raquettelover.repository.UserPlaceRepository;
import com.yonni.raquettelover.repository.UserRepository;
import com.yonni.raquettelover.security.CustomUserDetails;
import com.yonni.raquettelover.security.SecurityUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
@RequiredArgsConstructor
public class PlaceServiceImpl implements PlaceService {

    private final PlaceRepository placeRepository;
    private final UserService userService;
    private final UserPlaceRepository userPlaceRepository;

    @Override
    public void addPlace(PlaceDTO dto) {

        Place place = new Place();
        place.setName(dto.name());
        place.setAddress(dto.address());
        Place placeAdded = placeRepository.save(place);

        User user = userService.loadUserFromDTOorPrincipal(dto.userId());

        userPlaceRepository.save(new UserPlace(user, placeAdded));
    }

}
