package com.bms.booking.handlers;

import java.util.Calendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import com.bms.booking.models.BMSMovie;
import com.bms.booking.models.BMSTheater;
import com.bms.booking.requests.MovieRegisterPojo;
import com.bms.booking.requests.TheaterRegisterPojo;
import com.bms.booking.responses.ResponseBuilder;
import com.bms.booking.services.MovieService;
import com.bms.booking.services.TheaterSeatService;
import com.bms.booking.services.TheaterService;
import com.bms.booking.validations.MovieRegisterValidation;
import com.bms.booking.validations.TheaterServiceValidations;


@Component
public class MovieHandler {

	@Autowired
	private MovieRegisterValidation movieRegisterValidation;

	@Autowired
	private ResponseBuilder<String> responseBuilder;

	@Autowired
	MovieService movieService;
	
	@Autowired
	TheaterSeatService theaterSeatService;

	public ResponseEntity<String> registerMovie(MovieRegisterPojo movieRegisterRequest)
	{
		return processRegistration(movieRegisterRequest);
	}
	
	public ResponseEntity<String> processRegistration(MovieRegisterPojo movieRegisterRequest)
	{
		try {
			if(movieRegisterValidation.movieRegisterValidation(movieRegisterRequest)) {
				movieService.create(getModelObj(movieRegisterRequest));
				return responseBuilder.successResponse;	
			}
			return responseBuilder.validationFailedResponse;
		}
		catch(Exception exp)
		{
			return responseBuilder.ErrorSuccessResponse;
		}
	}

	
	public BMSMovie getModelObj(MovieRegisterPojo movie)
	{
		BMSMovie bmsMovie = new BMSMovie();
		bmsMovie.setName(movie.getName());
		bmsMovie.setReleaseAt(movie.getReleaseDate());
		bmsMovie.setCreatedAt(Calendar.getInstance().getTime());
		bmsMovie.setDeleted(0);
		return bmsMovie;
	}


}
