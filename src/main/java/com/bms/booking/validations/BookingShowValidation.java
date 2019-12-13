package com.bms.booking.validations;

import org.springframework.stereotype.Component;

import com.bms.booking.requests.BookingSeatPojo;
import com.bms.booking.requests.ReserveSeatPojo;

@Component
public class BookingShowValidation {

	public boolean reserveSeatValidation(ReserveSeatPojo reserveSeatPojo)
	{
		if(reserveSeatPojo!=null && reserveSeatPojo.getSeatnumbers()!=null & reserveSeatPojo.getSeatnumbers().size()>0)
			return true;
		return false;
	}
	public boolean bookSeatValidation(BookingSeatPojo bookingSeatPojo)
	{
		if(bookingSeatPojo!=null && bookingSeatPojo.getBookingKey()!=null)
			return true;
		return false;
	}
}
