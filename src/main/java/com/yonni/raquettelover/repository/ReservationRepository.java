package com.yonni.raquettelover.repository;

import com.yonni.raquettelover.entity.Court;
import com.yonni.raquettelover.entity.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {
}
