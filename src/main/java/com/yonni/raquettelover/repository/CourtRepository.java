package com.yonni.raquettelover.repository;

import com.yonni.raquettelover.entity.Court;
import com.yonni.raquettelover.entity.Place;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourtRepository extends JpaRepository<Court, Long> {
}
