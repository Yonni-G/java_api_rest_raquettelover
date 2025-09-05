package com.yonni.raquettelover.service;

import java.util.Optional;

import com.yonni.raquettelover.dto.ParticipationPlayerDto;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.yonni.raquettelover.dto.ParticipationGuestDto;
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
    public void addParticipation(ParticipationPlayerDto dto) {

        Optional<Reservation> reservationOpt = reservationRepository.findById(dto.reservationId());
        if (reservationOpt.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Réservation non trouvée");
        }

        // on vérifie que l'utilisateur n'est pas déjà inscrit à cette réservation
        if(participationRepository.existsByReservationIdAndUserId(dto.reservationId(), dto.userId())){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Utilisateur déjà inscrit à cette réservation");
        }

        // participation pour un utilisateur enregistré
        Participation participation = new Participation();
        participation.setReservation(reservationOpt.get());

        User user = userService.loadUserFromDTOorPrincipal(dto.userId());

        participation.setUser(user);
        participationRepository.save(participation);
    }

    @Override
    public void addParticipation(ParticipationGuestDto dto) {

        Optional<Reservation> reservationOpt = reservationRepository.findById(dto.reservationId());
        if (reservationOpt.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Réservation non trouvée");
        }

        // participation pour un invité
        Participation participation = new Participation();
        participation.setReservation(reservationOpt.get());
        participation.setFirstName(dto.guest().firstName());
        participation.setEmail(dto.guest().email());
        participation.setPhoneNumber(dto.guest().phoneNumber());
        participationRepository.save(participation);

    }
}
