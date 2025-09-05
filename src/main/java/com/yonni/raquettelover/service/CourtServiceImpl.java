package com.yonni.raquettelover.service;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.yonni.raquettelover.dto.CourtDto;
import com.yonni.raquettelover.entity.Court;
import com.yonni.raquettelover.entity.Place;
import com.yonni.raquettelover.entity.User;
import com.yonni.raquettelover.repository.CourtRepository;
import com.yonni.raquettelover.repository.PlaceRepository;
import com.yonni.raquettelover.repository.UserPlaceRepository;
import com.yonni.raquettelover.repository.UserRepository;
import com.yonni.raquettelover.security.CustomUserDetails;
import com.yonni.raquettelover.security.SecurityUtils;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CourtServiceImpl implements CourtService {

    private final PlaceRepository placeRepository;
    private final UserRepository userRepository;
    private final UserPlaceRepository userPlaceRepository;
    private final CourtRepository courtRepository;

    @Override
    public void addCourt(CourtDto dto) {

        CustomUserDetails principal = SecurityUtils.getCurrentUser();

        Place place = placeRepository.findById(dto.placeId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Lieu non trouvé"));

        // si le principal n'est pas admin, on vérifie qu'il est gestionnaire du lieu
        if (!SecurityUtils.isAdmin(principal)) {
            User user = userRepository.findById(principal.getId())
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Utilisateur non authentifié"));
            boolean isManagerOfPlace = userPlaceRepository.existsByUserAndPlace(user, place);
            if (!isManagerOfPlace) {
                throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Vous n'êtes pas autorisé à ajouter un terrain pour ce lieu");
            }
        }

        Court court = new Court();
        court.setName(dto.name());
        court.setDescription(dto.description());
        court.setType(dto.type());
        // on associe le terrain au lieu
        court.setPlace(place);

        courtRepository.save(court);
    }

}
