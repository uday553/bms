package com.bms.booking.handlers;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import com.bms.booking.dal.BookingRepository;
import com.bms.booking.dal.ShowsRepository;
import com.bms.booking.dal.TheaterSeatRepository;
import com.bms.booking.models.BMSBooking;
import com.bms.booking.models.BMSShows;
import com.bms.booking.models.BMSTheaterSeat;
import com.bms.booking.models.BookingStatus;
import com.bms.booking.requests.AddShowsPojo;
import com.bms.booking.requests.TheaterRegisterPojo;
import com.bms.booking.responses.ResponseBuilder;

@Component
public class ShowsHandler {

	@Autowired
	ShowsRepository showsRepository;
	
	@Autowired
	TheaterSeatRepository theaterSeatRepository;
	
	@Autowired
	BookingRepository bookingRepository;
	
	@Autowired
	ResponseBuilder<String> responseBuilder;
	
	public ResponseEntity<String> addShow(AddShowsPojo show)
	{
		return processAddShow(show);
	}
	
	public ResponseEntity<String> processAddShow(AddShowsPojo showsAddRequest)
	{
		if(showsAddRequest!=null)
		{
			BMSShows show = showsRepository.save(getBMSShows(showsAddRequest));
			List<BMSTheaterSeat> seats = theaterSeatRepository.findByTheaterIdAndDeleted(show.getTheaterId(), 0);
			List<BMSBooking> addShowSeats = new ArrayList<BMSBooking>();
			for(BMSTheaterSeat seat: seats)
			{
				BMSBooking seatBooking = new BMSBooking();
				seatBooking.setBookingStatus(BookingStatus.AVAILABLE);
				seatBooking.setCreatedAt(Calendar.getInstance().getTime());
				seatBooking.setDeleted(0);
				seatBooking.setShowId(show.getId());
				seatBooking.setSeatId(seat.getSeatNumber());
				addShowSeats.add(seatBooking);
			}
			bookingRepository.saveAll(addShowSeats);
			
		}
		return responseBuilder.successResponse;
	}
	
	public BMSShows getBMSShows(AddShowsPojo show)
	{
		BMSShows bmsShows = new BMSShows();
		bmsShows.setMovieId(show.getMovieId());
		bmsShows.setTheaterId(show.getTheaterId());
		bmsShows.setCreatedAt(Calendar.getInstance().getTime());
		return bmsShows;
	}
}
