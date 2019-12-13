package com.bms.booking.dal;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bms.booking.models.BMSShows;
import com.bms.booking.models.BMSTheaterSeat;

public interface ShowsRepository  extends JpaRepository<BMSShows, Long> {
	public List<BMSShows> findById(int id);
	
	public List<BMSShows> findByIdNotIn(List<Integer> ids);
	
}
