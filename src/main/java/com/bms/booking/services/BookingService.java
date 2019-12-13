package com.bms.booking.services;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.bms.booking.dal.BookingNativeDAO;
import com.bms.booking.dal.BookingRepository;
import com.bms.booking.dal.MovieRepository;
import com.bms.booking.dal.ShowsRepository;
import com.bms.booking.dal.TheaterSeatRepository;
import com.bms.booking.models.BMSBooking;
import com.bms.booking.models.BMSShows;
import com.bms.booking.models.BMSTheaterSeat;
import com.bms.booking.models.BookingStatus;
import com.bms.booking.requests.BookingSeatPojo;
import com.bms.booking.requests.ReserveSeatPojo;
import com.bms.booking.responses.ResponseBuilder;
import com.bms.booking.validations.BookingShowValidation;
import com.google.gson.Gson;

@Service
public class BookingService {

	@Autowired
	private BookingShowValidation bookingShowValidation;

	@PersistenceContext
	private EntityManager entityManager;

	@Autowired
	BookingRepository bookingRepository;

	@Autowired
	TheaterSeatRepository theaterSeatRepository;

	@Autowired
	MovieRepository movieRepository;

	@Autowired
	ShowsRepository showsRepository;

	@Autowired
	private ResponseBuilder<String> responseBuilder;

	@Autowired
	private BookingNativeDAO bookingNativeDAO; 

	public ResponseEntity<String> reserveSeats(ReserveSeatPojo reserveSeatPojo)
	{
		if(bookingShowValidation.reserveSeatValidation(reserveSeatPojo)) {
			List<BookingStatus> statusList = new ArrayList<BookingStatus>();
			statusList.add(BookingStatus.BOOKED);
			statusList.add(BookingStatus.RESERVED);
			List<BMSBooking> seatlist = bookingRepository.findByBookingStatusNotInAndSeatIdInAndShowId(statusList, reserveSeatPojo.getSeatnumbers(),reserveSeatPojo.getShowid());
			if(seatlist!=null && seatlist.size()>0 && seatlist.size()!= reserveSeatPojo.getSeatnumbers().size())
			{
				return responseBuilder.getSuccessResponse("Booking Not Possible for these seats");
			}
			String key = MD5(reserveSeatPojo.getSeatnumbers()+""+reserveSeatPojo.getShowid()+reserveSeatPojo.getMobileNumber()+Calendar.getInstance().getTime().getTime());
			List<BMSBooking> reserveSeatsList =  new ArrayList<BMSBooking>();
			for(BMSBooking reserve: seatlist)
			{
				reserve.setBookingStatus(BookingStatus.RESERVED);
				reserve.setContactNumber(reserveSeatPojo.getMobileNumber());
				reserve.setUpdatedAt(Calendar.getInstance().getTime());
				reserve.setBookingKey(key);
				reserveSeatsList.add(reserve);
			}
			bookingRepository.saveAll(reserveSeatsList);
			String response  ="{\"bookingKey\" :"+key+"}";
			return responseBuilder.getSuccessResponse(response);
		}
		return  responseBuilder.createdResponse;
	}

	public ResponseEntity<String> bookSeats(BookingSeatPojo bookingSeatPojo)
	{
		if(bookingShowValidation.bookSeatValidation(bookingSeatPojo)) {
			if(bookingNativeDAO.updateBookingStatus(bookingSeatPojo.getBookingKey()))
				return  responseBuilder.getSuccessResponse("Booking sucessful");
		}
		return  responseBuilder.getSuccessResponse("Booking failed");

	}

	public ResponseEntity<String> viewSeats(int showId)
	{
		if(showId>0) {
			List<BMSBooking> bookings = bookingRepository.findByShowIdAndDeleted(showId, 0);
			Map<String,List<Integer>> result =new HashMap<String, List<Integer>>();
			List<Integer> bookedSeats = new ArrayList<Integer>();
			List<Integer> reservedSeats = new ArrayList<Integer>();
			List<Integer> availableSeats = new ArrayList<Integer>();
			for(BMSBooking booking: bookings)
			{
				if(booking.getBookingStatus()==BookingStatus.BOOKED)
				{
					bookedSeats.add(booking.getSeatId());
				}
				if(booking.getBookingStatus()==BookingStatus.RESERVED)
				{
					reservedSeats.add(booking.getSeatId());
				}
				if(booking.getBookingStatus()==BookingStatus.AVAILABLE)
				{
					availableSeats.add(booking.getSeatId());
				}
			}
			result.put(BookingStatus.BOOKED.name(),bookedSeats);
			result.put(BookingStatus.RESERVED.name(),reservedSeats);
			result.put(BookingStatus.AVAILABLE.name(),availableSeats);

			return  responseBuilder.getSuccessResponse(getObjectToJson(result));
		}
		return  responseBuilder.getSuccessResponse("Booking failed");

	}


	public static String getObjectToJson(Object obj)
	{
		try {
			Gson gson = new Gson();
			return gson.toJson(obj);
		}
		catch(Exception exp) {
			return null;
		}
	}
	public String MD5(String md5) {
		try {
			java.security.MessageDigest md = java.security.MessageDigest.getInstance("MD5");
			byte[] array = md.digest(md5.getBytes());
			StringBuffer sb = new StringBuffer();
			for (int i = 0; i < array.length; ++i) {
				sb.append(Integer.toHexString((array[i] & 0xFF) | 0x100).substring(1,3));
			}
			return sb.toString();
		} catch (java.security.NoSuchAlgorithmException e) {
		}
		return null;
	}
}
