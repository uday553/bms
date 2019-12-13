package com.bms.booking.handlers;

import java.util.Calendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import com.bms.booking.models.BMSTheater;
import com.bms.booking.requests.TheaterRegisterPojo;
import com.bms.booking.responses.ResponseBuilder;
import com.bms.booking.services.TheaterSeatService;
import com.bms.booking.services.TheaterService;
import com.bms.booking.validations.TheaterServiceValidations;


@Component
public class TheaterHandler {

	@Autowired
	private TheaterServiceValidations theaterServiceValidations;

	@Autowired
	private ResponseBuilder<String> responseBuilder;

	@Autowired
	TheaterService theaterService;
	
	@Autowired
	TheaterSeatService theaterSeatService;

	public ResponseEntity<String> registerTheater(TheaterRegisterPojo theater)
	{
		return processRegistration(theater);
	}
	
	public ResponseEntity<String> closeTheater(int id)
	{
		return processClosingTheater(id);
	}
	

	public ResponseEntity<String> processClosingTheater(int id)
	{
		theaterService.delete(id);
		return responseBuilder.successResponse;	
	}
	
	public ResponseEntity<String> processRegistration(TheaterRegisterPojo theaterPojo)
	{
		try {
			if(theaterServiceValidations.isValidTheaterRegisterRequest(theaterPojo)) {
				theaterSeatService.addSeatsToTheater(theaterService.create(getModelObj(theaterPojo)).getId(), theaterPojo.getCapacity());
				return responseBuilder.successResponse;	
			}
			return responseBuilder.validationFailedResponse;
		}
		catch(Exception exp)
		{
			return responseBuilder.ErrorSuccessResponse;
		}
	}

	
	public BMSTheater getModelObj(TheaterRegisterPojo theater)
	{
		BMSTheater bmsTheater = new BMSTheater();
		bmsTheater.setAddress(theater.getAddress());
		bmsTheater.setName(theater.getName());
		bmsTheater.setCreatedAt(Calendar.getInstance().getTime());
		bmsTheater.setDeleted(0);
		return bmsTheater;
	}


}
