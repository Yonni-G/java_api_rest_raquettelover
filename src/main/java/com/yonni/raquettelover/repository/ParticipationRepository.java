package com.yonni.raquettelover.repository;

import com.yonni.raquettelover.entity.Participation;
import com.yonni.raquettelover.entity.Reservation;
import jakarta.servlet.http.Part;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ParticipationRepository extends JpaRepository<Participation, Long> {
}
