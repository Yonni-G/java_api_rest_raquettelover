package com.yonni.raquettelover.service;

import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.yonni.raquettelover.dto.ParticipationDto;
import com.yonni.raquettelover.entity.Participation;
import com.yonni.raquettelover.entity.Reservation;
import com.yonni.raquettelover.entity.User;
import com.yonni.raquettelover.repository.ParticipationRepository;
import com.yonni.raquettelover.repository.ReservationRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ParticipationServiceImpl implements ParticipationService {

    private final UserService userService;
    private final ParticipationRepository participationRepository;
    private final ReservationRepository reservationRepository;

    @Override
    public void addParticipation(ParticipationDto dto) {

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
