package com.bms.booking.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bms.booking.dal.TheaterRepository;
import com.bms.booking.models.BMSTheater;

@Service
public class TheaterService  implements Operations<BMSTheater> {

	@Autowired
	TheaterRepository theaterRepository;

	@Override
	public BMSTheater create(BMSTheater theater) {
		theater = theaterRepository.save(theater);
		return theater;
	}

	@Override
	public List<BMSTheater> read(int id) {
		return theaterRepository.findById(id);
	}

	@Override
	public void update(BMSTheater theater) {
		theaterRepository.save(theater);
	}

	@Override
	public void delete(int id) {
		theaterRepository.delete(id);
	}
	

}
