package com.bms.booking.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bms.booking.dal.MovieRepository;
import com.bms.booking.models.BMSMovie;

@Service
public class MovieService  implements Operations<BMSMovie> {

	@Autowired
	MovieRepository movieRepository;

	@Override
	public BMSMovie create(BMSMovie theater) {
		theater = movieRepository.save(theater);
		return theater;
	}

	@Override
	public List<BMSMovie> read(int id) {
		return movieRepository.findById(id);
	}

	@Override
	public void update(BMSMovie theater) {
		movieRepository.save(theater);
	}

	@Override
	public void delete(int id) {
		movieRepository.delete(id);
	}
	

}
