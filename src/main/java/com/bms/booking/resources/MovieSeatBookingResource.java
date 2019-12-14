package com.bms.booking.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.bms.booking.handlers.BookingHandler;
import com.bms.booking.handlers.MovieHandler;
import com.bms.booking.requests.AddShowsPojo;
import com.bms.booking.requests.BookingSeatPojo;
import com.bms.booking.requests.MovieRegisterPojo;
import com.bms.booking.requests.ReserveSeatPojo;

@RestController
@RequestMapping("/book/movie/seat")
public class MovieSeatBookingResource {

	@Autowired
	BookingHandler bookingHandler;

	// API for reserving seat
	@RequestMapping(value = "/reserve/v1", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<String> reserveSeat(@RequestBody ReserveSeatPojo reserveSeatPojo)
	{
		return bookingHandler.reserveSeat(reserveSeatPojo);
	}

	// API for booking ticket
	@RequestMapping(value = "/book/v1", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<String> bookSeat(@RequestBody BookingSeatPojo bookingSeatPojo)
	{
		System.out.println("Booking ");
		return bookingHandler.bookSeat(bookingSeatPojo);
	}
	
	// API to view seats
	@RequestMapping(value = "/view/v1/{showId}", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<String> viewSeats( @PathVariable("showId") int showId)
	{
		return bookingHandler.viewSeats(showId);
	}

}
