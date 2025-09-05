package com.yonni.raquettelover.service;

import com.yonni.raquettelover.DTO.PlaceDTO;
import com.yonni.raquettelover.DTO.ReservationDTO;
import com.yonni.raquettelover.entity.*;
import com.yonni.raquettelover.repository.*;
import com.yonni.raquettelover.security.CustomUserDetails;
import com.yonni.raquettelover.security.SecurityUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ReservationServiceImpl implements ReservationService {

    private final UserService userService;
    private final CourtRepository courtRepository;
    private final ReservationRepository reservationRepository;

    @Override
    public void addReservation(ReservationDTO dto) {

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
