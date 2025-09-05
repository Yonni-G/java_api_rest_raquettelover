package com.yonni.raquettelover.service;

import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.yonni.raquettelover.dto.ReservationDto;
import com.yonni.raquettelover.entity.Court;
import com.yonni.raquettelover.entity.Reservation;
import com.yonni.raquettelover.entity.User;
import com.yonni.raquettelover.repository.CourtRepository;
import com.yonni.raquettelover.repository.ReservationRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ReservationServiceImpl implements ReservationService {

    private final UserService userService;
    private final CourtRepository courtRepository;
    private final ReservationRepository reservationRepository;

    @Override
    public void addReservation(ReservationDto dto) {

        Optional<Court> courtOpt = courtRepository.findById(dto.courtId());
        if (courtOpt.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Court non trouvé");
        }

        User user = userService.loadUserFromDTOorPrincipal(dto.userId());

        Reservation reservation = new Reservation();
        reservation.setUser(user);
        reservation.setCourt(courtOpt.get());
        reservation.setReservationAt(dto.reservationAt());
        reservation.setStartHour(dto.startHour());
        reservation.setDuration(dto.duration());

        reservationRepository.save(reservation);
    }

}
