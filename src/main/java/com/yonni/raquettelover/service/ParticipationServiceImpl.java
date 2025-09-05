package com.yonni.raquettelover.service;

import com.yonni.raquettelover.DTO.ParticipationDTO;
import com.yonni.raquettelover.DTO.ReservationDTO;
import com.yonni.raquettelover.entity.Participation;
import com.yonni.raquettelover.entity.Reservation;
import com.yonni.raquettelover.entity.User;
import com.yonni.raquettelover.repository.CourtRepository;
import com.yonni.raquettelover.repository.ParticipationRepository;
import com.yonni.raquettelover.repository.ReservationRepository;
import com.yonni.raquettelover.repository.UserRepository;
import com.yonni.raquettelover.security.CustomUserDetails;
import com.yonni.raquettelover.security.SecurityUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ParticipationServiceImpl implements ParticipationService {

    private final UserService userService;
    private final ParticipationRepository participationRepository;
    private final ReservationRepository reservationRepository;

    @Override
    public void addParticipation(ParticipationDTO dto) {

        Optional<Reservation> reservationOpt = reservationRepository.findById(dto.reservationId());
        if (reservationOpt.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Réservation non trouvée");
        }

        Participation participation = new Participation();
        participation.setReservation(reservationOpt.get());

        User user = userService.loadUserFromDTOorPrincipal(dto.userId());

        participation.setUser(user);
        participationRepository.save(participation);
    }

}
