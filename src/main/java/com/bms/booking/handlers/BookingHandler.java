package com.bms.booking.handlers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import com.bms.booking.requests.BookingSeatPojo;
import com.bms.booking.requests.ReserveSeatPojo;
import com.bms.booking.responses.ResponseBuilder;
import com.bms.booking.services.BookingService;
import com.bms.booking.validations.BookingShowValidation;


@Component
public class BookingHandler {

	@Autowired
	private BookingShowValidation bookingShowValidation;

	@Autowired
	private ResponseBuilder<String> responseBuilder;

	@Autowired
	BookingService bookingService;

	public ResponseEntity<String> reserveSeat(ReserveSeatPojo reserveSeatPojo)
	{
		return bookingService.reserveSeats(reserveSeatPojo);
	}
	
	public ResponseEntity<String> bookSeat(BookingSeatPojo bookingSeatPojo)
	{
		return bookingService.bookSeats(bookingSeatPojo);
	}
	
	public ResponseEntity<String> viewSeats(int showId)
	{
		return bookingService.viewSeats(showId);
	}
	
	
}
